package com.antares.jinsei.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.antares.jinsei.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
}
