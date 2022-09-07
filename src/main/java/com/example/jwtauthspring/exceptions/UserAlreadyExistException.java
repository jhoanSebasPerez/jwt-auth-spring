package com.example.jwtauthspring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistException extends ResponseStatusException {

    public UserAlreadyExistException(String message){
        super(HttpStatus.CONFLICT, message);
    }
}
