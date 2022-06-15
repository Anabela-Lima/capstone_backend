package com.sgone.capstone.project.controller;


import com.sgone.capstone.dto.response.StandardResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class _TestAccessController {

    @GetMapping("/test")
    public ResponseEntity<StandardResponseDto<String>> testAccess() {
        return ResponseEntity
                .status(HttpStatus.I_AM_A_TEAPOT)
                .body(
                        new StandardResponseDto<>(
                                true,
                                "Tea party?",
                                "Test Success!")
                );
    }
}
