package com.apptrove.ledgerlyBackend.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apptrove.ledgerlyBackend.entities.TransactionRecords;
import com.apptrove.ledgerlyBackend.entities.GLAccntMst;
import com.apptrove.ledgerlyBackend.entities.ApartmentMst;



public interface TxnRecordsRepository extends JpaRepository<TransactionRecords, Integer> {

	public List<TransactionRecords> findByAuthStatus(Integer authStatus);
	
	public List<TransactionRecords> findByGlAccountAndAuthStatus(GLAccntMst glAccount, Integer authStatus);
	
	@Query("FROM TransactionRecords WHERE DATE(startDate)=DATE(:startDate) AND DATE(endDate)=DATE(:endDate)")
	public List<TransactionRecords> findByStartDateBetween(@Param("startDate") Date startDate,@Param("endDate") Date endDate);
	
	public List<TransactionRecords> findByAptmntAndAuthStatus(ApartmentMst aptmnt, Integer authStatus);
}
