package com.example.jwtauthspring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundUserException extends ResponseStatusException {

    public NotFoundUserException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
