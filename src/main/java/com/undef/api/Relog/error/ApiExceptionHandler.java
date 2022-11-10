package com.undef.api.Relog.error;

import com.undef.api.Relog.exception.GenericAlreadyExists;
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

    @ExceptionHandler(value = GenericAlreadyExists.class)
    protected ResponseEntity<Object> handleAlreadyExists(RuntimeException ex, WebRequest request) {
        var response = ErrorResponse.builder()
                .status(HttpStatus.CONFLICT)
                .message(ex.getMessage())
                .errors(Arrays.asList("El recurso ya existe")).build();
        return handleExceptionInternal(ex,response,new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
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
