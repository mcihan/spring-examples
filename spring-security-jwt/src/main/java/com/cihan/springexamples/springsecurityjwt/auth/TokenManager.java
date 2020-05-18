package com.cihan.springexamples.springsecurityjwt.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenManager {
    private String secretKey = "springSecurityExample";
    private int timeRemaining = 2 * 60 * 1000;
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(String userName){
        Date createDate = new Date(System.currentTimeMillis());
        Date expireDate = new Date(createDate.getTime()+ timeRemaining);

        String jws = Jwts.builder()
                .setSubject(userName)
                .setIssuer("cihanIssuer")
                .setIssuedAt(createDate)
                .setExpiration(expireDate)
                .signWith(key)
                .compact();
        return jws;
    }
    public boolean isValidToken(String token){
        if(getUserFromToken(token) != null && isExpired(token)){
            return true;
        }
        return false;
    }

    public String getUserFromToken(String token){
        Claims claims = getClaim(token);
        return claims.getSubject();
    }

    public boolean isExpired(String token){
        Claims claims = getClaim(token);
        return claims.getExpiration().after(new Date(System.currentTimeMillis()));
    }

    private Claims getClaim(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
    private Claims getClaimNew(String token) {
        return (Claims) Jwts.parserBuilder().setSigningKey(key).build().parse(token).getBody();
    }
}
