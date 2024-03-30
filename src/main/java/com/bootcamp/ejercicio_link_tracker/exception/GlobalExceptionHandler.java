package com.bootcamp.ejercicio_link_tracker.exception;

import com.bootcamp.ejercicio_link_tracker.dto.response.GenericResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFound(NotFoundException e){
        return new ResponseEntity<>(new GenericResponseDTO(e.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<?> authException(AuthException e){
        return new ResponseEntity<>(new GenericResponseDTO(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
