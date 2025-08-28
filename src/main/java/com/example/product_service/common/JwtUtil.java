package com.example.product_service.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "My jwt secret key";
    private static final int EXPIRATION = 1000 * 60 * 60 * 10;

    public Claims extracAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractEmail(String token){
        return extracAllClaims(token).getSubject();
    }
    public boolean isTokenExpired(String token){
        return extracAllClaims(token).getExpiration().before(new Date());
    }

    public String generateToken(String email, Role role){
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorization", role);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        return (userDetails.getUsername().equals(extractEmail(token)) && !isTokenExpired(token));
    }
}
