package com.fpt.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;


public final class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
//		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write("{\"message\":\"Unauthoried\"}");
	}
}
