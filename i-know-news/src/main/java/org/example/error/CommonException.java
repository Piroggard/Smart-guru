package org.example.error;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;



@Builder
public class CommonException extends RuntimeException {
    private final String code;
    private final String userMessage;
    private final String techMessage;
    private final HttpStatus httpStatus;
}