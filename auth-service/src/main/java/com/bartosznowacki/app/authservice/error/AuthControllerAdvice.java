package com.bartosznowacki.app.authservice.error;

import com.bartosznowacki.app.authservice.shared.error.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.dao.DuplicateKeyException;

import static com.bartosznowacki.app.authservice.error.ErrorConstants.*;

@ControllerAdvice
class AuthControllerAdvice {

    @ExceptionHandler(UsernameNotFoundException.class)
    ResponseEntity<ErrorDto> handleIncorrectUserException() {
        return new ResponseEntity<>(new ErrorDto(
                ERROR_USER_NOT_FOUND_CODE,
                ERROR_USER_NOT_FOUND_MESSAGE),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<ErrorDto> handleIncorrectCredentialsException() {
        return new ResponseEntity<>(new ErrorDto(
                ERROR_BAD_CREDENTIALS_CODE,
                ERROR_BAD_CREDENTIALS_MESSAGE),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    ResponseEntity<ErrorDto> handleDuplicateKeyException() {
        return new ResponseEntity<>(new ErrorDto(
                ERROR_USER_ALREADY_EXISTS_CODE,
                ERROR_USER_ALREADY_EXISTS_MESSAGE),
                HttpStatus.BAD_REQUEST);
    }
}
