package com.apptrove.ledgerlyBackend.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apptrove.ledgerlyBackend.entities.ApartmentMst;
import com.apptrove.ledgerlyBackend.entities.GLAccntMst;
import com.apptrove.ledgerlyBackend.entities.TransactionMst;
import com.apptrove.ledgerlyBackend.entities.TransactionRecords;
import com.apptrove.ledgerlyBackend.payload.GLAccntTxn;
import com.apptrove.ledgerlyBackend.payload.TransactionAuthorModel;
import com.apptrove.ledgerlyBackend.payload.TransactionMakerModel;
import com.apptrove.ledgerlyBackend.payload.TransactionMstType;
import com.apptrove.ledgerlyBackend.repository.GLAccntMstRepository;
import com.apptrove.ledgerlyBackend.repository.TxnMstRepository;
import com.apptrove.ledgerlyBackend.repository.TxnRecordsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TxnRecordServiceImpl implements TxnRecordService {
	
	private static final Logger logger = LogManager.getLogger(TxnRecordServiceImpl.class);
	
	private final TxnRecordsRepository txnRecordsRepository;
	
	private final TxnMstRepository txnMstRepository;
	
	private final GLAccntMstRepository glAccntMstRepository;
	
	private final ModelMapper modelMapper;
	
	@Transactional
	@Override
	public Map<String, Object> makerTransactionRecord(TransactionMakerModel transactionMakerModel) {
		TransactionRecords transactionRecord = new TransactionRecords();
		Map<String,Object> respObject = new HashMap<String, Object>();
		try {
			logger.info("Inside makerTransactionRecord");
			transactionRecord = modelMapper.map(transactionMakerModel, TransactionRecords.class);
			transactionRecord.setMakerDt(new Date());
			GLAccntMst glAccnt = glAccntMstRepository.findById(transactionMakerModel.getGlAccntId()).orElseThrow(() -> new ResourceNotFoundException("GL with Id: "+transactionMakerModel.getGlAccntId()+" not found"));
			if (transactionRecord.getAuthStatus() == null) {
				transactionRecord.setAuthStatus(0);
			}
			transactionRecord.setGlAccount(glAccnt);
			transactionRecord.setGlAccntBal(glAccnt.getAccntBal());
			transactionRecord = txnRecordsRepository.save(transactionRecord);
			respObject.put("transactionRecords", transactionRecord);
			respObject.put("flag",true);
			logger.info("Exiting from makerTransactionRecord");
		} catch (Exception e) {
			logger.info("An error occurred: "+e.getMessage());
			respObject.put("flag", false);
			e.printStackTrace();
		}
		return respObject;
	}

	@Transactional
	@Override
	public Map<String, Object> authorTransactionRecord(TransactionAuthorModel transactionAuthorModel) {
		TransactionRecords transactionRecords = new TransactionRecords();
		Map<String,Object> respObject = new HashMap<String, Object>();
		try {
			logger.info("Inside authorTransactionRecord method");
			transactionRecords = txnRecordsRepository.findById(transactionAuthorModel.getTransactionId())
					.orElseThrow(() -> new ResourceNotFoundException("Transaction with Id: "+transactionAuthorModel.getTransactionId()+" not found"));
			if (transactionRecords.getAuthStatus() == 0) {
				GLAccntMst glAccnt = transactionRecords.getGlAccount();
				BigDecimal bal =  new BigDecimal(0);
				if ("Cr".equals(transactionRecords.getTransactionType())) {
					bal = glAccnt.getAccntBal().add(transactionRecords.getTransactionAmnt());
				} else if("Db".equals(transactionRecords.getTransactionType())) {
					bal = glAccnt.getAccntBal().subtract(transactionRecords.getTransactionAmnt());
				} else {
					throw new RuntimeException("Illegal Transaction Type: "+transactionRecords.getTransactionType());
				}
				transactionRecords.setGlAccntBal(bal);
				transactionRecords.setAuthorCd(transactionAuthorModel.getAuthorCd());
				transactionRecords.setAuthorDt(new Date());
				transactionRecords.setAuthStatus(transactionAuthorModel.getAuthStatus());
				transactionRecords.setAuthorRmrks(transactionAuthorModel.getAuthorRmrks());
				txnRecordsRepository.saveAndFlush(transactionRecords);
				glAccnt.setAccntBal(bal);
				glAccnt = glAccntMstRepository.saveAndFlush(glAccnt);
				respObject.put("transactionRecords", transactionRecords);
				respObject.put("message", "Record Updation Successful!");
				respObject.put("flag", true);
				logger.info("Record Updation Successful for record Id: "+transactionAuthorModel.getTransactionId());
			} else {
				respObject.put("transactionRecords", null);
				respObject.put("message", "Record Already Authored!");
				respObject.put("flag", false);
				logger.info("Record Already Authored for record Id: "+transactionAuthorModel.getTransactionId());
			}
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			respObject.put("message", e.getMessage());
			respObject.put("flag", false);
			e.printStackTrace();
		}
		return respObject;
	}

	@Override
	public List<TransactionRecords> getUnauthorizedTxnList() {
		List<TransactionRecords> txnRecList = new ArrayList<TransactionRecords>();
		try {
			logger.info("Inside getUnauthorizedTxnList method");
			txnRecList = txnRecordsRepository.findByAuthStatus(0);
			logger.info("Exiting getUnauthorizedTxnList method");
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
		}
		return txnRecList;
	}

	@Override
	public Map<String, Object> getTxnMstData() {
	    Map<String, Object> respObject = new HashMap<>();
	    
	    try {
	        logger.info("Inside getTxnMstData method");
	        
	        // Fetch transaction master data
	        TransactionMst txnCategoryTypeData = txnMstRepository.findByParamNameAndIsActive("txn_category", true);
	        TransactionMst txnTypeData = txnMstRepository.findByParamNameAndIsActive("txn_category_type", true);
	        TransactionMst txnAmntData = txnMstRepository.findByParamNameAndIsActive("txn_amnt", true);

	        logger.info("Transaction Category Data: " + txnCategoryTypeData);
	        logger.info("Transaction Type Data: " + txnTypeData);
	        logger.info("Transaction Amount Data: " + txnAmntData);

	        List<TransactionMstType> categoryList = new ArrayList<TransactionMstType>();
	        List<TransactionMstType> typeList = new ArrayList<TransactionMstType>();
	        List<TransactionMstType> amountList = new ArrayList<TransactionMstType>();

	        if (txnCategoryTypeData != null && txnCategoryTypeData.getParamValue() != null) {
	            String[] catArray = txnCategoryTypeData.getParamValue().split("\\|");
	            for (String string : catArray) {
					String[] catString = string.split("=");
					TransactionMstType obj = new TransactionMstType();
					obj.setId(Integer.parseInt(catString[0]));
					obj.setName(catString[1]);
					
					categoryList.add(obj);
 				}
	        }

	        if (txnTypeData != null && txnTypeData.getParamValue() != null) {
	            String[] typeArray = txnTypeData.getParamValue().split("\\|");
	            for (String string : typeArray) {
					String[] typeString = string.split("=");
					TransactionMstType obj = new TransactionMstType();
					obj.setId(Integer.parseInt(typeString[0]));
					obj.setName(typeString[1]);
					
					typeList.add(obj);
 				}
	        }

	        if (txnAmntData != null && txnAmntData.getParamValue() != null) {
	            String[] amntArray = txnAmntData.getParamValue().split("\\|");
	            for (String string : amntArray) {
					String[] amntString = string.split("=");
					TransactionMstType obj = new TransactionMstType();
					obj.setId(Integer.parseInt(amntString [0]));
					obj.setName(amntString[1]);
					
					amountList.add(obj);
 				}
	        }

	        // Add processed maps to response object
	        respObject.put("txnCategoryTypeData", categoryList);
	        respObject.put("txnTypeData", typeList);
	        respObject.put("txnAmntData", amountList);

	        logger.info("Exiting getTxnMstData method");

	    } catch (Exception e) {
	        logger.error("An error occurred: " + e.getMessage(), e);
	    }

	    return respObject;
	}

	@Override
	public List<TransactionRecords> getAllAuthorizedTxnRecords() {
		List<TransactionRecords> txnRecList = new ArrayList<TransactionRecords>();
		try {
			logger.info("Inside getAllAuthorizedTxnRecords method");
			txnRecList = txnRecordsRepository.findByAuthStatus(1);	
			logger.info("Exiting getAllAuthorizedTxnRecords method after finding {} records",txnRecList.size());
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
		}
		return txnRecList;
	}

	@Override
	public List<GLAccntTxn> getTransactionsAccordingToGL(Integer glAccntId) {
		List<GLAccntTxn> glAccntTxnList = new ArrayList<GLAccntTxn>();
		List<TransactionRecords> txnRecList = new ArrayList<TransactionRecords>();
		try {
			logger.info("Inside getTransactionsAccordingToGL method for GL with id: "+glAccntId);
			GLAccntMst glAccntMst = glAccntMstRepository.findByGlAccntIdAndIsActive(glAccntId, 1)
					.orElseThrow(() -> new ResourceNotFoundException("GL Account with Id: " + glAccntId + " not found"));
			
			txnRecList = txnRecordsRepository.findByGlAccountAndAuthStatus(glAccntMst,1);
			logger.info("Found {} number of transactions for GL Account",txnRecList.size());
			glAccntTxnList = txnRecList.stream()
					.map(txnRecord -> this.modelMapper.map(txnRecord, GLAccntTxn.class))
					.peek(glAccntTxn -> glAccntTxn.setGlAccntId(glAccntId))
					.collect(Collectors.toList());
			logger.info("Exiting getTransactionsAccordingToGL method for GL with id: "+glAccntId);
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
		}
		return glAccntTxnList;
	}

	@Override
	public List<GLAccntMst> getAllGlAccnts() {
		List<GLAccntMst> glAccntList = new ArrayList<GLAccntMst>();
		try {
			logger.info("Inside getAllGlAccnts method");
			glAccntList = glAccntMstRepository.findByIsActive(1);
			logger.info("Found {} Gl Account(s)",glAccntList.size());
			logger.info("Exiting getAllGlAccnts method");
		} catch (Exception e) {
			logger.error("An error occurred: {}",e.getMessage());
			e.printStackTrace();
		}
		return glAccntList;
	}

	@Override
	public List<TransactionRecords> findByApartment(ApartmentMst aptmnt) {
		List<TransactionRecords> txnRecordList = new ArrayList<TransactionRecords>();
		try {
			logger.info("Inside findByApartment method for apartment no: {}",aptmnt.getAptmntNo());
			txnRecordList = this.txnRecordsRepository.findByAptmntAndAuthStatus(aptmnt,1);
			logger.info("Exiting findByApartment method after finding {} transactions",txnRecordList.size());
		} catch (Exception e) {
			logger.error("An error occurred: {}",e.getMessage());
			e.printStackTrace();
		}
		return txnRecordList;
	}
	
	

}
