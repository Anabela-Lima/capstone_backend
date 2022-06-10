package com.sgone.capstone.jwt;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgone.capstone.dto.request.UsernameAndPasswordAuthenticationRequestDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException {

        try {
            UsernameAndPasswordAuthenticationRequestDto authenticationRequestDto =
                    new ObjectMapper()
                            .readValue(
                                    request.getInputStream(),
                                    UsernameAndPasswordAuthenticationRequestDto.class
                            );

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequestDto.getUsername(),
                    authenticationRequestDto.getPassword()
            );

            return authenticationManager.authenticate(authentication);
        }
        catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {

        String key = "haveASecureKeyHereThatShouldBeLongAndReallyReallySecureAsWell";

        String token = Jwts.builder()
                            // Set the header
                            .setSubject(authResult.getName())
                            // Set the body
                            .claim("authorities", authResult.getAuthorities())
                            .setIssuedAt(new Date())
                            .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                            .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                            .compact();

        response.addHeader("Authorization", "Bearer " + token);
    }
}
