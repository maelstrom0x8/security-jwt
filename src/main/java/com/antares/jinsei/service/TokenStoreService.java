package com.antares.jinsei.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.antares.jinsei.model.Token;
import com.antares.jinsei.model.User;
import com.antares.jinsei.repository.TokenRepository;

@Service
public class TokenStoreService {

	private TokenRepository tokenRepository;


	public TokenStoreService(TokenRepository tokenRepository) {
		this.tokenRepository = tokenRepository;

		Executors.newSingleThreadScheduledExecutor()
			.scheduleAtFixedRate(this::revokeExpiredTokens, 10, 10, TimeUnit.MINUTES);
	}

	public Token create(User user) {
		String tokenID = generateId();
		Token token = new Token(
				tokenID, user, LocalDateTime.now().plusDays(2),
				null);

		return tokenRepository.save(token);
	}

	public boolean validateToken(String tokenID) {
		Optional<Token> token = tokenRepository.findById(tokenID);

		if (token.isPresent()
				&& LocalDateTime.now().isBefore(token.get().getExpiry())) {
			return true;
		}

		return false;

	}

	public void revokeExpiredTokens() {
		var now = LocalDateTime.now();
		tokenRepository.deleteExpiredTokens(now);
	}

	private String generateId() {
		var bytes = new byte[20];
		new SecureRandom().nextBytes(bytes);
		return Base64.getUrlEncoder().encodeToString(bytes);
	}

}
