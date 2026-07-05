package com.hemendra.auth_service.util;


import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key secretkey;

    // application.properties
    //jwt.secret=mySecretKey123abc
    //@Value = secrets kept outside the codebase
    public JwtUtil(@Value("${jwt.secret}") String secret){

        byte[] keyBytes = Base64.getDecoder().decode(secret.getBytes(StandardCharsets.UTF_8));

        this.secretkey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email, String role){
        return Jwts.builder()
                .subject(email)
                .claim("role",role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60*60*10))
                .signWith(secretkey)
                .compact();
    }


    public void validateToken(String token) {
        try{
            Jwts.parser().verifyWith((SecretKey) secretkey)
                    .build()
                    .parseSignedClaims(token);
        }
//        catch (SignatureException e)
//        {
//            throw new JwtException("Invalid Signature");
//        }
        catch (JwtException e)
        {
            throw new JwtException("invalid JWT");
        }
    }
}
