package com.sadev.security.utils;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.sadev.security.jwtService.UserDetailsServiceImpl;
import com.sadev.security.model.User;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtUtils {

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Value("${jwt.utils.secret}")
	private String jwtSecret;
	@Value("${jwt.utils.expiration.duration}")
	private long expirationMs;

	public String generateJwtToken(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		SecretKeySpec secretKey = new SecretKeySpec(jwtSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
		return Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + this.expirationMs)).signWith(secretKey).compact();
	}

	public boolean validateToken(String token) {
		try {
			SecretKeySpec secretKey = new SecretKeySpec(jwtSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
			Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			System.err.println("Invalid JWT signature: {} " + e.getMessage());
		} catch (MalformedJwtException e) {
			System.err.println("Invalid JWT token: {} " + e.getMessage());
		} catch (ExpiredJwtException e) {
			System.err.println("JWT token is expired: {} " + e.getMessage());
		} catch (UnsupportedJwtException e) {
			System.err.println("JWT token is unsupported: {} " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.err.println("JWT claims string is empty: {} " + e.getMessage());
		}
		return false;
	}

	public String getUsernameFromToken(String token) {
		SecretKeySpec secretKey = new SecretKeySpec(jwtSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
		return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
	}
}
