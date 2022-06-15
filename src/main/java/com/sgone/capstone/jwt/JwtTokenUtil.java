package com.sgone.capstone.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.function.Function;

/**
 * A JWT token consist of three parts separated by a dot.
 * <br><br>
 * <b>Header.Payload.Signature</b>
 * <br><br>
 * The <b>Header</b> typically consists of two parts, the type of token and the signing algorithm used.
 * <br><br>
 * The <b>Payload</b> contains the claims, which are statements about an entity, in this case it is the user. <br>For this
 * project the claims contains the authorities given to a user. When the user makes a request to any endpoints, the
 * user's authorities will be checked to see if this user should be allowed access.
 * <br><br>
 * The <b>Signature</b> is used to verify the message wasn't changed along the way, and in the case of tokens signed
 * with a private key, it can also verify that the sender of the JWT is who it says it is.
 * <br><br>
 * <i><b>source:</b></i> https://jwt.io/introduction
 */
@Component
public class JwtTokenUtil implements Serializable {

    @Autowired
    private JwtConfig jwtConfig;

    public String generateToken(UserDetails userDetails) {
        return Jwts
                .builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userDetails.getUsername())
                .claim("authorities", userDetails.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes()))
                .compact();
    }
}
