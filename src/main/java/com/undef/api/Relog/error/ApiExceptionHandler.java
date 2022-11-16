package com.undef.api.Relog.error;

import com.undef.api.Relog.exception.GenericAlreadyExistsExeption;
import com.undef.api.Relog.exception.GenericNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = GenericAlreadyExistsExeption.class)
    protected ResponseEntity<Object> handleAlreadyExists(RuntimeException ex, WebRequest request) {
        var response = ErrorResponse.builder()
                .status(HttpStatus.CONFLICT)
                .message(ex.getMessage())
                .errors(Arrays.asList("El recurso ya existe")).build();
        return handleExceptionInternal(ex,response,new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = GenericNotFoundException.class)
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        var response = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .errors(Arrays.asList("El recurso no ha sido encontrado")).build();
        return handleExceptionInternal(ex,response,new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorDefaultMessages = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().stream()
                .forEach(fieldError ->  {
                    errorDefaultMessages.add(fieldError.getField() +
                            ": " + fieldError.getDefaultMessage());
                });

        var errorResponse  = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .errors(errorDefaultMessages)
                .message(ex.getMessage())
                .build();

        return handleExceptionInternal(ex,errorResponse,headers, errorResponse.getStatus(), request);
    }

}
