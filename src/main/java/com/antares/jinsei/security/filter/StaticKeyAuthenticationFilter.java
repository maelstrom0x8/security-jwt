package com.antares.jinsei.security.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StaticKeyAuthenticationFilter implements Filter {

	@Value("${jinsei.authorization.key}")
	private String jinseiAuthorizationKey = "antares";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String authentication = httpRequest.getHeader("Authorization");

		if (jinseiAuthorizationKey.equals(authentication)) {
			chain.doFilter(request, response);
		} else {
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}

	}

}
