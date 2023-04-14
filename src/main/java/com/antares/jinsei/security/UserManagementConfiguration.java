package com.antares.jinsei.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserManagementConfiguration {
    

    @Bean
    UserDetailsService userdetailsService() {
        UserDetails bob = User.withUsername("bob")
            .password(passwordEncoder().encode("12345"))
            .authorities("read", "write")
            .build();

        return new InMemoryUserDetailsManager(bob);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
