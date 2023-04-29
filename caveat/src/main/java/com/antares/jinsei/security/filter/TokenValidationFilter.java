package com.antares.jinsei.security.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.antares.jinsei.service.TokenStoreService;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TokenValidationFilter implements Filter {

	@Autowired
	private TokenStoreService tokenStoreService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String header = httpRequest.getHeader("Authorization");
		String token = header.substring(7);

		if (token == null || token.startsWith("Bearer ")) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		if (!tokenStoreService.validateToken(token)) {
			httpResponse.setHeader("WWW-Authenticate", "Bearer error=\"invalid token\"" +
					"error_description=\"Expired\"");
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		chain.doFilter(httpRequest, httpResponse);
	}

}
