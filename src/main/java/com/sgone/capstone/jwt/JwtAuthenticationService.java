package com.sgone.capstone.jwt;

import com.sgone.capstone.service.auth.AuthenticationApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthenticationApplicationUserService authenticationApplicationUserService;


    public UserDetails tryAuthentication(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    username,
                    password
            ));
            return authenticationApplicationUserService.loadUserByUsername(username);
        }
        catch (DisabledException de) {
            throw new RuntimeException("This user has been disabled, please contact support.");
        }
        catch (LockedException le) {
            throw new RuntimeException("This account is locked, please contact support.");
        }
        catch (BadCredentialsException bce) {
            throw new RuntimeException("The username and password provided are incorrect!");
        }
    }


}

