package com.yael.springboot.api.gchat.gchat.presentation.Interceptors;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.yael.springboot.api.gchat.gchat.domain.exceptions.CustomException;




@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException ex) {
        return this.getResponse(ex.getMessage(), ex.getStatus());
    }


    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<?> handleDeniedException( AuthorizationDeniedException ex ){
        return getResponse("Only authorized users can access this action", 401);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException( MethodArgumentNotValidException ex ){
        List<Map<String, String>> errors = ex.getBindingResult().getFieldErrors().stream().map(error -> {
            Map<String, String> errorDetails = new HashMap<>();
            errorDetails.put("field", error.getField());
            errorDetails.put("message", error.getDefaultMessage());

            return errorDetails;
        }).collect(Collectors.toList());


        return getResponse(errors, 400);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataBaseException( DataIntegrityViolationException ex ){
        String messageError = "Internal server error, try again later.";
        int status = 500;
        String fieldError = ex.getMessage().substring( ex.getMessage().indexOf('(') + 1, ex.getMessage().indexOf(')') );

        if( !fieldError.isBlank() && fieldError.length() > 0 ){
            messageError = "Account with: ";
            messageError += fieldError;
            messageError += " exists";
            status = 400;
        }

        return getResponse(messageError, status);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        //TODO: logger;
        System.out.println(ex);

        return this.getResponse("Internal server error, please try again later.", 500);
    }


    private ResponseEntity<?> getResponse(Object messageErrors, int status){
        Map<String, Object> response = new HashMap<>();

        response.put("date", new Date());
        response.put("err", messageErrors);
        response.put("status", status);

        return ResponseEntity.status(status).body(response);
    }
}
