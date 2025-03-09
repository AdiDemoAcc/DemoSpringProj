package com.apptrove.ledgerlyBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apptrove.ledgerlyBackend.entities.ApartmentMst;
import com.apptrove.ledgerlyBackend.entities.ApartmentOccupant;
import java.util.List;


public interface ApartmentOccupantRepository extends JpaRepository<ApartmentOccupant, Integer> {

	public List<ApartmentOccupant> findByIsActive(Integer isActive);
	
	public List<ApartmentOccupant> findByAptmnt(ApartmentMst aptmnt);
	
}
