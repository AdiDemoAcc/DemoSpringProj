package com.apptrove.ledgerlyBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apptrove.ledgerlyBackend.entities.MenuItemMst;
import java.util.List;


public interface MenuItemMstRepository extends JpaRepository<MenuItemMst, Integer> {

	public List<MenuItemMst> findByMenuSubIdIn(List<Integer> menuSubIdList);
	
}
