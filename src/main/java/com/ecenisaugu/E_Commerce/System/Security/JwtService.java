package com.ecenisaugu.E_Commerce.System.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ecenisaugu.E_Commerce.System.Entity.UserEntites.User;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("86400000")
    private long expirationTime;

    public String generateToken(User userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraclaims, User userDetails) {
        return buildJwt(extraclaims, userDetails);
    }

    public String buildJwt(Map<String, Object> extraclaims, User userDetails) {
        return Jwts.builder()
                .setClaims(extraclaims)
                .setSubject(userDetails.getUsername())
                //.setSubject(userDetails.getUserId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSignKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    

    public String extractUserEmail(String token) {
        return extractClaims(token , Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaims(token , Claims::getExpiration);
    }
    public boolean isTokenExpired(String token) {
        return extractClaims(token , Claims::getExpiration).before(new Date());
    }

    public boolean isTokenValid(String token , UserDetails userDetails) {
        return extractClaims(token , Claims::getSubject).equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private Key getSignKey(){
        byte[] codes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(codes);
    }
    public <T> T extractClaims(String token , Function<Claims , T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
