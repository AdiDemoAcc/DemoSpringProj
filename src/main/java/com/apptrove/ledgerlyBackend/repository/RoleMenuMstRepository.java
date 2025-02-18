package com.apptrove.ledgerlyBackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apptrove.ledgerlyBackend.entities.RoleMenuMst;


public interface RoleMenuMstRepository extends JpaRepository<RoleMenuMst, Integer>{

	public Optional<? extends RoleMenuMst> findByRoleIdAndIsActive(Integer roleId, boolean isActive);
	
}
