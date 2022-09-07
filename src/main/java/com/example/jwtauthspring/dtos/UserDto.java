package com.example.jwtauthspring.dtos;

import com.example.jwtauthspring.domain.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserDto {

    @Email
    @NotNull
    @Length(min = 5, max = 50)
    private String email;

    @NotNull
    @Length(min = 5, max = 50)
    private String username;

    @NotNull
    @Length(min = 5, max = 10)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User toUser(){
        User user = new User();
        user.setEmail(this.email);
        user.setUsername(this.username);
        user.setPassword(this.password);

        return user;
    }
}
