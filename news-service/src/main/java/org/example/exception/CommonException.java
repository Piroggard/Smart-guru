package org.example.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;



@Builder
public class CommonException extends RuntimeException {
    private final String code;
    private final String userMessage;
    private final String techMessage;
    private final HttpStatus httpStatus;
}