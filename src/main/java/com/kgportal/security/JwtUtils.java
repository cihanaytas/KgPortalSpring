package com.kgportal.security;

import java.util.Date;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import static com.kgportal.security.SecurityConstants.*;

@Component
public class JwtUtils {
 
 

	public String generateJwtToken(Authentication authentication) {


		return Jwts.builder()
                .setSubject(((User) authentication.getPrincipal()).getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
				.compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token).getBody().getSubject();
	}

 
}