package com.sgone.capstone.security.authentication;

import com.sgone.capstone.project.model.ApplicationUser;
import com.sgone.capstone.security.authentication.AuthenticationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthenticationUserDetailsService authenticationUserDetailsService;
    @Autowired
    private AuthenticationUserDetailsRepository authenticationUserDetailsRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserDetails tryAuthentication(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    username,
                    password
            ));
            return authenticationUserDetailsService.loadUserByUsername(username);
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


    public UserDetails trySignUp(String username, String password) {

        Optional<ApplicationUser> userOptional =
                authenticationUserDetailsRepository.findUserByUsername(username.trim());

        if (userOptional.isPresent()) {
            throw new RuntimeException("User already exists, sign in instead?");
        }

        authenticationUserDetailsRepository
                .save(
                        new ApplicationUser(
                                username.trim(),
                                passwordEncoder.encode(password.trim()),
                                null,
                                null,
                                "test_name",
                                false,
                                false
                        )
                );

        try {
            return tryAuthentication(username, password);
        }
        catch (RuntimeException re) {
            throw new RuntimeException(re.getMessage());
        }
    }

}

