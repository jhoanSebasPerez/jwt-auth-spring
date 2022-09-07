package com.example.jwtauthspring.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class AuthRequest {

    @NotNull
    @Length(min = 5, max = 50)
    private String username;

    @NotNull
    @Length(min = 5, max = 10)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
