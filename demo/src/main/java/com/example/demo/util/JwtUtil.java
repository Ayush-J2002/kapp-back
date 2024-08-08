package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {
   private String secretKey="gh3fue7637489450872543#$$$^%^%hhgjhk#$#$5gjhJHK87879bhgjht43557gHJhk7687798njkkl8789hiu9";
   private SecretKey getSignInKey() {
    return Keys.hmacShaKeyFor(secretKey.getBytes());

}
    

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public String extractRoles(String token) {
        return extractClaim(token, claims -> claims.get("role",String.class));
        // return extractClaim(token,Claims::g)
        
    }
    

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token ) {
    
    return Jwts.parserBuilder()
               .setSigningKey(getSignInKey())
               .build()
               .parseClaimsJws(token)
               .getBody();
}

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails,String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles",role);
        // return createToken(claims, userDetails.getUsername());
            return Jwts.builder()
                    .setSubject(userDetails.getUsername())
                    .addClaims(claims)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiration
                    .signWith(getSignInKey())
                    .compact();
        }

    

    //private SecretKey secretKey= Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // private String createToken(Map<String, Object> claims, String subject) {
    //     return Jwts.builder()
    //                .setClaims(claims)
    //                .setSubject(subject)
    //                .setIssuedAt(new Date(System.currentTimeMillis()))
    //                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
    //                .signWith(secretKey)
    //                .compact();
    // }


   

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}

