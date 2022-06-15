package com.sgone.capstone.security.authentication;

import com.sgone.capstone.dto.request.NewUserDto;
import com.sgone.capstone.dto.request.UsernamePasswordAuthenticationRequestDto;
import com.sgone.capstone.dto.response.StandardResponseDto;
import com.sgone.capstone.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/authenticate")
    public ResponseEntity<StandardResponseDto<?>> createAuthToken(
            @RequestBody UsernamePasswordAuthenticationRequestDto requestDto
    ) {

        try {
            UserDetails user = authenticationService.tryAuthentication(
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



    @PostMapping("/sign_up")
    public ResponseEntity<StandardResponseDto<?>> createNewUser(
            @RequestBody NewUserDto newUserDto
    ) {



        return null;
    }
}
