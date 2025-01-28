package com.apptrove.ledgerlyBackend.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apptrove.ledgerlyBackend.entities.TransactionRecords;
import com.apptrove.ledgerlyBackend.payload.TransactionAuthorModel;
import com.apptrove.ledgerlyBackend.payload.TransactionMakerModel;
import com.apptrove.ledgerlyBackend.repository.TxnRecordsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TxnRecordServiceImpl implements TxnRecordService {
	
	private static final Logger logger = LogManager.getLogger(TxnRecordServiceImpl.class);
	
	private final TxnRecordsRepository txnRecordsRepository;
	
	private final ModelMapper modelMapper;
	
	@Transactional
	@Override
	public Map<String, Object> makerTransactionRecord(TransactionMakerModel transactionMakerModel) {
		TransactionRecords transactionRecords = new TransactionRecords();
		Map<String,Object> respObject = new HashMap<String, Object>();
		try {
			logger.info("Inside makerTransactionRecord:::::::::::::::::::::::::::::::::::::::");
			transactionRecords = modelMapper.map(transactionMakerModel, TransactionRecords.class);
			transactionRecords.setMakerDt(new Date());
			transactionRecords = txnRecordsRepository.save(transactionRecords);
			respObject.put("transactionRecords", transactionRecords);
			respObject.put("flag",true);
			logger.info("Exiting from makerTransactionRecord:::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			logger.info("An error occurred: "+e.getMessage());
			respObject.put("flag", false);
			e.printStackTrace();
		}
		return respObject;
	}

	@Override
	public Map<String, Object> authorTransactionRecord(TransactionAuthorModel transactionAuthorModel) {
		TransactionRecords transactionRecords = new TransactionRecords();
		Map<String,Object> respObject = new HashMap<String, Object>();
		try {
			logger.info("Inside authorTransactionRecord method:::::::::::::::::::::::::::::::::::::::::::::");
			transactionRecords = txnRecordsRepository.findById(transactionAuthorModel.getTransactionId())
					.orElseThrow(() -> new ResourceNotFoundException("Transaction with Id: "+transactionAuthorModel.getTransactionId()+" not found"));
			transactionRecords.setAuthorCd(transactionAuthorModel.getAuthorCd());
			transactionRecords.setAuthorDt(new Date());
			transactionRecords.setAuthorRmrks(transactionAuthorModel.getAuthorRmrks());
			txnRecordsRepository.saveAndFlush(transactionRecords);
			respObject.put("transactionRecords", transactionRecords);
			respObject.put("flag", true);
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			respObject.put("flag", false);
			e.printStackTrace();
		}
		return respObject;
	}

}
