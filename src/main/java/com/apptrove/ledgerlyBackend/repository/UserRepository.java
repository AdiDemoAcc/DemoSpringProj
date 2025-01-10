package com.apptrove.ledgerlyBackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.apptrove.ledgerlyBackend.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public boolean existsByUsername(String username);
	
	@EntityGraph(attributePaths = "roles")
	public Optional<? extends User> findByUsername(String username); 
}
