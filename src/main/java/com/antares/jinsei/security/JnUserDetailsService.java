package com.antares.jinsei.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.antares.jinsei.model.User;
import com.antares.jinsei.repository.UserRepository;
import com.antares.jinsei.service.TokenStoreService;

@Service
public class JnUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private TokenStoreService tStoreService;

	public String createUser(String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));

		userRepository.save(user);
		return tStoreService.create(user)
			.getId();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);

		if(!user.isPresent())
			throw new UsernameNotFoundException("User does not exist");

		return new JnUserDetails(user.get());
	}
	
}
