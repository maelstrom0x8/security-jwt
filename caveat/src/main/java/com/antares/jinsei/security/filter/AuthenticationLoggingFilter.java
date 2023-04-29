package com.antares.jinsei.security.filter;

import java.io.IOException;
import java.util.logging.Logger;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class AuthenticationLoggingFilter implements Filter {

	private final Logger logger = Logger.getLogger(AuthenticationLoggingFilter.class.getName());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String requestId = httpRequest.getHeader("Request-Id");

		logger.info("Successfully authenticated request with id " + requestId);
		chain.doFilter(request, response);
	}
	
}
