package org.example.review.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestException extends ResponseStatusException {
    public BadRequestException(HttpStatus badRequest, String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}