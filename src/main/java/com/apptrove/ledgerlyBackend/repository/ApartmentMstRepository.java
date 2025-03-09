package com.apptrove.ledgerlyBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apptrove.ledgerlyBackend.entities.ApartmentMst;
import java.util.List;


public interface ApartmentMstRepository extends JpaRepository<ApartmentMst, Integer> {

	public List<ApartmentMst> findByIsActive(Integer isActive);
	
	public ApartmentMst findByAptmntIdAndIsActive(Integer aptmntId, Integer isActive);
}
