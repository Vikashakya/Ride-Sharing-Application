package com.cdac.ridesharingapplication.constant;

import java.util.Base64;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
* Author: Admin
* Date: 5 Feb 2025
**/
public class AppConstants {
	
    public static final String JWT_TOKEN_SECRET = Base64.getEncoder().encodeToString(
            Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded());
    public static final long JWT_TOKEN_VALIDITY = 15 * 60 * 1000; 
    
}
