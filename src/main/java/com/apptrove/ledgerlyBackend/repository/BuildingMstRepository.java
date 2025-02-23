package com.apptrove.ledgerlyBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apptrove.ledgerlyBackend.entities.BuildingMst;

public interface BuildingMstRepository extends JpaRepository<BuildingMst, Integer> {

	public List<BuildingMst> findByIsActive(Integer isActive);
	
}
