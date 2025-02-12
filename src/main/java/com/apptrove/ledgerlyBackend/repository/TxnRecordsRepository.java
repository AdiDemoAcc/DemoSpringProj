package com.apptrove.ledgerlyBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apptrove.ledgerlyBackend.entities.TransactionRecords;
import com.apptrove.ledgerlyBackend.entities.GLAccntMst;


public interface TxnRecordsRepository extends JpaRepository<TransactionRecords, Integer> {

	public List<TransactionRecords> findByAuthStatus(Integer authStatus);
	
	public List<TransactionRecords> findByGlAccountAndAuthStatus(GLAccntMst glAccount, Integer authStatus);
}
