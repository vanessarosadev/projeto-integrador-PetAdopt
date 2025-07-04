/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.api.petadopt.exception;

/**
 *
 * @author vanes
 */
import java.util.HashMap; 
import java.util.Map; 
import org.springframework.http.HttpHeaders; 
import org.springframework.http.HttpStatus; 
import org.springframework.http.HttpStatusCode; 
import org.springframework.http.ResponseEntity; 
import org.springframework.validation.FieldError; 
import org.springframework.web.bind.MethodArgumentNotValidException; 
import org.springframework.web.context.request.WebRequest; 
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler; 
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {
    @Override 
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
    HttpHeaders headers, HttpStatusCode status, WebRequest request) { 

        Map<String, String> errors = new HashMap<>(); 

        ex.getBindingResult().getAllErrors().forEach((error) -> { 

            String fieldName = ((FieldError) error).getField(); 

            String message = error.getDefaultMessage(); 

            errors.put(fieldName, message); 

        }); 

        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST); 
    }
}
