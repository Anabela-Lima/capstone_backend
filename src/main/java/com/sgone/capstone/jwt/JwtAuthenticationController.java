package com.sgone.capstone.jwt;

import com.sgone.capstone.dto.request.UsernameAndPasswordAuthenticationRequestDto;
import com.sgone.capstone.dto.response.StandardResponseDto;
import com.sgone.capstone.service.auth.AuthenticationApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtAuthenticationService jwtAuthenticationService;


    @PostMapping("/authenticate")
    public ResponseEntity<StandardResponseDto<?>> createAuthToken(
            @RequestBody UsernameAndPasswordAuthenticationRequestDto requestDto
    ) {

        try {
            UserDetails user = jwtAuthenticationService.tryAuthentication(
                    requestDto.getUsername(),
                    requestDto.getPassword()
            );
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new StandardResponseDto<>(
                            true,
                            "Token generated",
                            jwtTokenUtil.generateToken(user)
                    ));
        }
        catch (RuntimeException re) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new StandardResponseDto<>(
                            false,
                            re.getMessage(),
                            null
                    ));
        }

    }
}
