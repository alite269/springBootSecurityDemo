package com.alite.springBootSecurityDemo.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JWTService {

    @Autowired
    private AuthenticationManager authenticationManager;

    private final String KEY = "FHIRMiddlewareFHIRMiddlewareFHIRMiddleware"; //FHIRMiddleware

    public String generateToken(AuthRequest request) {
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        authentication = authenticationManager.authenticate(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 10);

        Claims claims = Jwts.claims();
        claims.put("username", userDetails.getUsername());
//        List<> = userDetails.getAuthorities();
        claims.put("role", userDetails.getAuthorities());
        claims.setExpiration(calendar.getTime());
        claims.setIssuer("Programming Classroom");

        Key secretKey = Keys.hmacShaKeyFor(KEY.getBytes());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(secretKey)
                .compact();
    }

    public  Claims parseToken(String token) throws ExpiredJwtException  {
        Key secretKey = Keys.hmacShaKeyFor(KEY.getBytes());

        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build();
        return parser
                    .parseClaimsJws(token)
                    .getBody();	
    }
}
