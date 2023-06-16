package com.quathar.api.data.service.impl;

import com.quathar.api.data.entity.User;
import com.quathar.api.data.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <h1>JSON Web Token (JWT) Service Implementation</h1>
 *
 * @since 2023-06-16
 * @version 1.0
 * @author Q
 */
@Service
public class JwtServiceImpl implements JwtService {

    // <<-FIELDS->>
    private final byte[] _secretKeyByteArray;

    // <<-CONSTRUCTOR->>
    public JwtServiceImpl(
            @Value("${jwt.secret-key}")
            String secretKey
    ) {
        _secretKeyByteArray = secretKey.getBytes();
    }

    // <<-METHODS->>
    @Override
    public String createJwt(User user) {
        // If new Date use the def constructor it will be the current date and time
        Date now = new Date();
        long expirationTime = 10 * 60 * 1000; // 10 minutes

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expirationTime))
                .signWith(Keys.hmacShaKeyFor(_secretKeyByteArray))
                .compact();
    }

    @Override
    public Claims extractClaims(String jwt)
            throws UnsupportedJwtException,
                   MalformedJwtException,
                   ExpiredJwtException,
                   IllegalArgumentException
    {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(_secretKeyByteArray))
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    @Override
    public User extractUser(String jwt) {
        Claims claims = extractClaims(jwt);
        return new User(
                null,
                claims.getSubject(),
                ""
        );
    }

}
