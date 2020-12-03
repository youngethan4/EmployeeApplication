package com.tcs.employeerestapi.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.employeerestapi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Transactional
	Optional<User> findByUsername(String username);
	public Boolean existsByUsername(String username);
	public Boolean existsByEmail(String email);
}
