package com.mergetechng.jobs.commons.dto;

public class AuthenticationLoginDto {
    private String username;
    private String password;

    public AuthenticationLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthenticationLoginDto() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
