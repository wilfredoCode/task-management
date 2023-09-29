package com.test.taskmanagement.exception;

import javax.persistence.PersistenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomHttpExeception.class)
    public ResponseEntity<ExceptionResp> handlerOfCustomHttp(CustomHttpExeception ex) {
        ExceptionResp exception = new ExceptionResp(ex.getStatus().value(), ex.getDescription());
        return ResponseEntity.status(ex.getStatus()).body(exception);
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<ExceptionResp> handlerOfPersistenceException(PersistenceException ex) {
        log.error(ex.getMessage());
        String message = "";
        message = "incorrect input values";
        log.error(message);
        ExceptionResp exception = new ExceptionResp(HttpStatus.BAD_REQUEST.value(), message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    }
}

