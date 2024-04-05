package org.mihai.error.handler;

import lombok.extern.slf4j.Slf4j;
import org.mihai.error.ErrorResponse;
import org.mihai.error.exceptions.UserException;
import org.mihai.error.exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(UserException.class)
    public ErrorResponse handleUserError(UserException ex) {
        log.error("Error", ex);
        return new ErrorResponse(ex.getMessage(), BAD_REQUEST.value());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handleUserNotFoundError(UserNotFoundException ex) {
        log.error("Error", ex);
        return new ErrorResponse(ex.getMessage(), NOT_FOUND.value());
    }


    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleError(Exception ex) {
        log.error("Error", ex);
        return new ErrorResponse(ex.getMessage(), INTERNAL_SERVER_ERROR.value());
    }


}
