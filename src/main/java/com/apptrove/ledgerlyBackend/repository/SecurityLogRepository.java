package com.apptrove.ledgerlyBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apptrove.ledgerlyBackend.entities.SecurityLog;
import java.util.List;
import java.util.Date;


public interface SecurityLogRepository extends JpaRepository<SecurityLog, Integer> {

	List<SecurityLog> findByUsernameAndLoginDtAndLogoutDt(String username, Date loginDt, Date logoutDt);
	
}
