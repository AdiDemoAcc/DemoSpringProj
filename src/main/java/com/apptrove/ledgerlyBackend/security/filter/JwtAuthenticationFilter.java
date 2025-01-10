package com.apptrove.ledgerlyBackend.security.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.apptrove.ledgerlyBackend.entities.User;
import com.apptrove.ledgerlyBackend.security.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final UserDetailsService userDetailsService;

	private final JwtUtil jwtUtil;

	private final HandlerExceptionResolver handlerExceptionResolver;
	
	public JwtAuthenticationFilter(UserDetailsService userDetailsService, JwtUtil jwtUtil,
			HandlerExceptionResolver handlerExceptionResolver) {
		super();
		this.userDetailsService = userDetailsService;
		this.jwtUtil = jwtUtil;
		this.handlerExceptionResolver = handlerExceptionResolver;
	}



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			final String authorizationHeader = request.getHeader("Authorization");

			String username = null;
			String token = null;

			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				token = authorizationHeader.substring(7);
				username = jwtUtil.extractUsername(token);
			}
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				User user = (User) userDetailsService.loadUserByUsername(username);

				if (jwtUtil.validateToken(token, user)) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null,
							user.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}

			filterChain.doFilter(request, response);
		} catch (Exception exception) {
			handlerExceptionResolver.resolveException(request, response, null, exception);
			exception.printStackTrace();
		}
	}

}
