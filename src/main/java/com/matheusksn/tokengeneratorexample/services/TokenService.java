package com.matheusksn.tokengeneratorexample.services;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private static final int TOKEN_EXPIRATION_DAYS = 10;
    private static final int TOKEN_LENGTH = 64;
    private static final SecureRandom secureRandom = new SecureRandom();

    public String generateToken() {
        byte[] tokenBytes = new byte[TOKEN_LENGTH];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }

    public boolean isTokenValid(String token) {
        return token != null && !token.isEmpty();
    }
}
