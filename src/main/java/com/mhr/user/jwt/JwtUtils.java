package com.mhr.user.jwt;

import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtils {
	
	private final String Secret = "mySecret";

	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.signWith(SignatureAlgorithm.HS256, Secret)
				.setExpiration(new Date(System.currentTimeMillis() + (60*60*24*1000) ))
				.compact();		
	}
	
	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(Secret).parseClaimsJws(token).getBody().getSubject();
	}
	
}
