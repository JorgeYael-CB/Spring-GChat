package com.yael.springboot.api.gchat.gchat.presentation.Interceptors;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.yael.springboot.api.gchat.gchat.domain.exceptions.CustomException;




@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException ex) {
        Map<String, Object> response = new HashMap<>();

        response.put("error: ", ex.getMessage());
        response.put("date", new Date());
        response.put("status", ex.getStatus());

        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException( MethodArgumentNotValidException ex ){
        Map<String, Object> response = new HashMap<>();

        List<Map<String, String>> errors = ex.getBindingResult().getFieldErrors().stream().map(error -> {
            Map<String, String> errorDetails = new HashMap<>();
            errorDetails.put("field", error.getField());
            errorDetails.put("message", error.getDefaultMessage());

            return errorDetails;
        }).collect(Collectors.toList());

        response.put("errors", errors);
        response.put("date", new Date());
        response.put("status", 400);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        //TODO: logger;
        System.out.println(ex);

        Map<String, Object> response = new HashMap<>();

        response.put("error: ", "Internal server error, please try again later.");
        response.put("date", new Date());
        response.put("status", 500);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
