package com.apptrove.ledgerlyBackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apptrove.ledgerlyBackend.entities.Role;
import com.apptrove.ledgerlyBackend.entities.RoleEnum;


public interface RoleRepository extends JpaRepository<Role, Integer> {

	public boolean existsByRoleId(Integer roleId);
	
	public Optional<? extends Role> findByRoleName(RoleEnum roleName);
	
}
