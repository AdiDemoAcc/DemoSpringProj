package com.apptrove.ledgerlyBackend.service;

import java.util.List;
import java.util.Map;

import com.apptrove.ledgerlyBackend.entities.GLAccntMst;
import com.apptrove.ledgerlyBackend.entities.TransactionRecords;
import com.apptrove.ledgerlyBackend.payload.GLAccntTxn;
import com.apptrove.ledgerlyBackend.payload.TransactionAuthorModel;
import com.apptrove.ledgerlyBackend.payload.TransactionMakerModel;

public interface TxnRecordService {

	public Map<String, Object> makerTransactionRecord(TransactionMakerModel transactionMakerModel);
	
	public Map<String, Object> authorTransactionRecord(TransactionAuthorModel transactionAuthorModel);
	
	public List<TransactionRecords> getUnauthorizedTxnList();
	
	public Map<String, Object> getTxnMstData();
	
	public List<TransactionRecords> getAllAuthorizedTxnRecords();
	
	public List<GLAccntTxn> getTransactionsAccordingToGL(Integer glAccntId);
	
	public List<GLAccntMst> getAllGlAccnts();
}
