package com.example.springbootbackend.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.hibernate.internal.util.StringHelper;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {

    private static final String SECRET = "Anshad";

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T>claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .setSigningKey(getSignKey())
                .parseClaimsJws(token)
                .getBody();
    }

    private SecretKeySpec getSignKey(){
        return new SecretKeySpec(SECRET.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }

    public  boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private boolean validateToken(String token, String username){
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    public String generateToken(String username){
        return createToken(username);
    }

    private String createToken(String username){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 1000*60*60);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, getSignKey())
                .compact();
    }


}
