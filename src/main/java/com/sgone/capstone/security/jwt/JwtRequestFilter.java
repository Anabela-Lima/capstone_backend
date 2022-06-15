package com.sgone.capstone.security.jwt;

import com.google.common.base.Strings;
import com.sgone.capstone.security.authentication.AuthenticationUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * This is the first filter in the Spring security filter chain. It implements the
 * OncePerRequestFilter from Spring Boot. It guarantees to run just once per request.
 * This filter is responsible for checking if the request contains an Authorization Bearer token.
 * If the request does contain a token and it is a valid, the user will be authenticationed and added
 * to the security context.
 */

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationUserDetailsService authenticationUserDetailsService;
    @Autowired
    private JwtConfig jwtConfig;

    public JwtRequestFilter() {}

    public JwtRequestFilter(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException {

        // Grabs authorization value from the request header
        String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());

        /*
            Check to see if there is an authorization header or if the token starts with "Bearer "
            If there is no authorization header or the token does not start with 'Bearer ' then we go to the next filter
         */
        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }

        // Grab the actual token by removing the prefix "Bearer "
        String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");

        try {
            /*
                Parse the JWT token to verify its validity, if it isn't valid, an IllegalStateException will be thrown.
             */
            Jws<Claims> claimsJws = Jwts.parser()
                                        .setSigningKey(Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes()))
                                        .parseClaimsJws(token);

            // If the token is valid we get the body of the token
            Claims body = claimsJws.getBody();

            // Get the username and expiration date from the payload of the JWT
            String username = body.getSubject();
            Date expiration = body.getExpiration();

            // If the token has expired then we reject it and move onto to authentication
            if (expiration.before(new Date())) {
                filterChain.doFilter(request, response);
                return;
            }

            // We grab all the authorities attributed to this user
            List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");

            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities
                    .stream()
                    .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                    .collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    simpleGrantedAuthorities
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String userName;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof UserDetails) {
                userName = ((UserDetails)principal).getUsername();
            } else {
                userName = principal.toString();
            }

            System.out.println(userName);

        } catch (JwtException e) {
            throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
        }

        filterChain.doFilter(request, response);
    }
}
