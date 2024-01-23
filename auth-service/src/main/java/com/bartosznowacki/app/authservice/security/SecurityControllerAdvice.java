package com.bartosznowacki.app.authservice.security;

import com.bartosznowacki.app.authservice.shared.ErrorDto;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import static com.bartosznowacki.app.authservice.shared.ErrorConstants.*;
import static com.google.common.collect.Sets.newHashSet;

@ControllerAdvice
class SecurityControllerAdvice extends AbstractControllerAdvice {
    SecurityControllerAdvice(Environment environment) {
        super(newHashSet(ERROR_BAD_CREDENTIALS_CODE, ERROR_BAD_CREDENTIALS_MESSAGE), environment);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    ResponseEntity<ErrorDto> handleIncorrectUserException() {
        return new ResponseEntity<>(new ErrorDto(
                errorsMap.get(ERROR_USER_NOT_FOUND_CODE),
                errorsMap.get(ERROR_USER_NOT_FOUND_MESSAGE)),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<ErrorDto> handleIncorrectCredentialsException() {
        return new ResponseEntity<>(new ErrorDto(
                errorsMap.get(ERROR_BAD_CREDENTIALS_CODE),
                errorsMap.get(ERROR_BAD_CREDENTIALS_MESSAGE)),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    ResponseEntity<ErrorDto> handleDuplicateKeyException() {
        return new ResponseEntity<>(new ErrorDto(
                errorsMap.get(ERROR_USER_ALREADY_EXISTS_CODE),
                errorsMap.get(ERROR_USER_ALREADY_EXISTS_MESSAGE)),
                HttpStatus.BAD_REQUEST);
    }
}
