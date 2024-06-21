package com.org.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DuplicateCertificateException extends ResponseStatusException {
    public DuplicateCertificateException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
