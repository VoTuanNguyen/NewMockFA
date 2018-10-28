
package com.fpt.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import com.fpt.model.LoginModel;
import com.fpt.service.AccountService;
import com.fpt.service.JwtService;

public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

	private final static String TOKEN_HEADER = "authorization";

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AccountService accountService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authToken = httpRequest.getHeader(TOKEN_HEADER);
//		System.out.println(authToken);
		try {
			if (jwtService.validateTokenLogin(authToken)) {
				String username = jwtService.getUsernameFromToken(authToken);

				LoginModel user = accountService.findUserByUserName(username);
				if (user != null) {
					boolean enabled = true;
					boolean accountNonExpired = true;
					boolean credentialsNonExpired = true;
					boolean accountNonLocked = true;
					UserDetails userDetail = new User(username, user.getPassword(), enabled, accountNonExpired,
							credentialsNonExpired, accountNonLocked, user.getAuthorities());

					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetail, null, userDetail.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		} catch (Exception e) {
			System.out.println("Invalid format token!");
		}

		chain.doFilter(request, response);
	}
}