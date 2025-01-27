package com.apptrove.ledgerlyBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apptrove.ledgerlyBackend.entities.TransactionRecords;

public interface TxnRecordsRepository extends JpaRepository<TransactionRecords, Integer> {

	
	
}
