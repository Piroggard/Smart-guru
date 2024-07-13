package org.example.controller.handler;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.example.dto.ErrorResponseDto;
import org.example.exception.IncorrectUserActionException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(IncorrectUserActionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleIncorrectUserActionException(IncorrectUserActionException e) {
        log.error("Incorrect user action exception ", e);
        return ErrorResponseDto.builder()
                .message("This user can't do this action")
                .status(HttpStatus.BAD_REQUEST)
                .dateTime(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleEntityNotFoundException(EntityNotFoundException e) {
        log.error("Entity not found exception ", e);
        return ErrorResponseDto.builder()
                .message("Sorry, it's couldn't be found")
                .status(HttpStatus.NOT_FOUND)
                .dateTime(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleUsernameNotFoundException(UsernameNotFoundException e) {
        log.error("Username not found exception ", e);
        return ErrorResponseDto.builder()
                .message("This user couldn't be found")
                .status(HttpStatus.BAD_REQUEST)
                .dateTime(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponseDto handleBadCredentialsException(BadCredentialsException e) {
        log.error("Bad credentials for user", e);
        return ErrorResponseDto.builder()
                .message("Incorrect password entered")
                .status(HttpStatus.FORBIDDEN)
                .dateTime(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleException(Exception e) {
        log.error("Exception ", e);
        return ErrorResponseDto.builder()
                .message("Sorry, server error")
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .dateTime(LocalDateTime.now())
                .build();
    }
}
