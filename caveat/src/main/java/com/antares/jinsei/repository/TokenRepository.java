package com.antares.jinsei.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.antares.jinsei.model.Token;

public interface TokenRepository extends JpaRepository<Token, String> {
	
	@Modifying
    @Query("DELETE FROM Token t WHERE t.expiry < :expiry")
    void deleteExpiredTokens(@Param("expiry") LocalDateTime expiry);
}
