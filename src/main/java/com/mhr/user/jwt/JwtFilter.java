package com.mhr.user.jwt;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mhr.user.model.User;
import com.mhr.user.serviece.UserServiceImpl;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		String jwt = request.getHeader("Authorization");
	
		if(jwt != null) {
			String username = jwtUtils.getUsername(jwt);
			
			if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				User user = (User) userServiceImpl.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, null ,user.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(token);
			}
		}
		
		filterChain.doFilter(request, response);
	}

	
}
