package com.apptrove.ledgerlyBackend.security.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

	@Value("${jwt-secret}")
	private String jwt_secret;
	
}
