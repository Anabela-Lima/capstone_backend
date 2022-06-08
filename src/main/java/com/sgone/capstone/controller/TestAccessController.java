package com.sgone.capstone.controller;


import com.sgone.capstone.dto.StandardResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAccessController {

    @GetMapping("/test")
    public ResponseEntity<StandardResponseDto<String>> testAccess() {
        return ResponseEntity.status(HttpStatus.OK).body(new StandardResponseDto<>(true, "This is a test endpoint", "Test Success!"));
    }
}
