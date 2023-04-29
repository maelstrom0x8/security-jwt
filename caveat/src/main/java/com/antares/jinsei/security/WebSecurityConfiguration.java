package com.antares.jinsei.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.antares.jinsei.security.filter.TokenValidationFilter;

@Configuration
public class WebSecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors(cors -> {
            CorsConfigurationSource source = request -> {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOriginPatterns(
                        List.of("http://localhost:3000"));
                config.setAllowedMethods(
                        List.of("GET", "POST", "DELTE", "PUT", "PATCH"));
                return config;
            };

            cors.configurationSource(source);
        });

        http.csrf(csrf -> csrf.disable());

        http.addFilterAfter(new TokenValidationFilter(),
                UsernamePasswordAuthenticationFilter.class);

        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/account/register", "/account/login")
                .permitAll();
            auth.anyRequest().authenticated();
        });
        return http.build();
    }

}
