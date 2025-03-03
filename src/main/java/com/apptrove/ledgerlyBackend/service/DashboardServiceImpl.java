package com.apptrove.ledgerlyBackend.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.apptrove.ledgerlyBackend.entities.TransactionRecords;
import com.apptrove.ledgerlyBackend.repository.TxnRecordsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

	private static final Logger logger = LogManager.getLogger(DashboardServiceImpl.class);
	
	private final TxnRecordsRepository txnRecordsRepository;
	
//	private final ModelMapper modelMapper;
	
	@Override
	public Map<String,Object> getCurrMonthTxnRecords() {
		Map<String,Object> summary = new HashMap<String,Object>();
		BigDecimal totalCredit = BigDecimal.ZERO;
		BigDecimal totalDebit = BigDecimal.ZERO;
		Map<Integer,BigDecimal> aptmntPayments = new HashMap<Integer,BigDecimal>();
		try {
			logger.info("Inside getCurrMonthTxnRecords method");
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.HOUR_OF_DAY,0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			Date startDate = cal.getTime();
			
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			cal.set(Calendar.HOUR_OF_DAY,23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			Date endDate = cal.getTime();
			
			logger.info("Fetching transactions between {} and {}", startDate, endDate);
			
			List<TransactionRecords> txnRecordList = txnRecordsRepository.findByStartDateBetween(startDate, endDate);
			
			logger.info("Got {} current month transactions",txnRecordList.size());
			
			for (TransactionRecords records : txnRecordList) {
				BigDecimal amount = records.getTransactionAmnt();
				
				if ("CR".equalsIgnoreCase(records.getTransactionType())) {
					totalCredit = totalCredit.add(amount);
				} else if ("DB".equalsIgnoreCase(records.getTransactionType())) {
					totalDebit = totalDebit.add(amount);
				}
				
				if (records.getAptmntId() != null) {
					aptmntPayments.put(records.getAptmntId(), aptmntPayments.getOrDefault(records.getAptmntId(), BigDecimal.ZERO).add(amount));
				}
				
			}
			
			summary.put("totalCredit", totalCredit);
			summary.put("totalDebit", totalDebit);
			summary.put("aptmntPayments", aptmntPayments);
			
			
			logger.info("Exiting getCurrMonthTxnRecords method");
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
		}
		return summary;
	}

}
