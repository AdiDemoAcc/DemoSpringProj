package com.apptrove.ledgerlyBackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apptrove.ledgerlyBackend.entities.GLAccntMst;

public interface GLAccntMstRepository extends JpaRepository<GLAccntMst, Integer>{

	public boolean existsByGlAccntIdAndIsActive(Integer glAccntId,Integer isActive);
	
	public Optional<?extends GLAccntMst> findByGlAccntIdAndIsActive(Integer glAccntId,Integer isActive);
	
	public List<GLAccntMst> findByIsActive(Integer isActive);
	
}
