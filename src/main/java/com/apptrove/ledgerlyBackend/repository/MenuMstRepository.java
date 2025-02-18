package com.apptrove.ledgerlyBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apptrove.ledgerlyBackend.entities.MenuMst;

public interface MenuMstRepository extends JpaRepository<MenuMst, Integer>{

	public List<MenuMst> findByMenuIdIn(List<Integer> menuIdList);
	
}
