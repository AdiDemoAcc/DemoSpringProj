package com.apptrove.ledgerlyBackend.service;

import java.util.Map;

import com.apptrove.ledgerlyBackend.payload.TransactionAuthorModel;
import com.apptrove.ledgerlyBackend.payload.TransactionMakerModel;

public interface TxnRecordService {

	public Map<String, Object> makerTransactionRecord(TransactionMakerModel transactionMakerModel);
	
	public Map<String, Object> authorTransactionRecord(TransactionAuthorModel transactionAuthorModel);
	
}
