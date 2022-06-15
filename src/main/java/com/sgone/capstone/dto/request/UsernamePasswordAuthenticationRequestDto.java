package com.sgone.capstone.dto.request;

public class UsernamePasswordAuthenticationRequestDto {

    public String username;
    public String password;

    public UsernamePasswordAuthenticationRequestDto() {}

    public UsernamePasswordAuthenticationRequestDto(String username,
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
