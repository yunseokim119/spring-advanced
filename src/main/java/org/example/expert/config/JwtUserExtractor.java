package org.example.expert.config;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtUserExtractor {
    private final JwtUtil jwtUtil;

    @Autowired
    public JwtUserExtractor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public long extractUserId(String bearerToken) {
        Claims claims = jwtUtil.extractClaims(bearerToken.substring(7));
        return Long.parseLong(claims.getSubject());
    }
}
