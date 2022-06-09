package com.sgone.capstone.dto;

public class UsernameAndPasswordAuthenticationRequestDto {

    public String username;
    public String password;

    public UsernameAndPasswordAuthenticationRequestDto() {}

    public UsernameAndPasswordAuthenticationRequestDto(String username,
                                                       String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
