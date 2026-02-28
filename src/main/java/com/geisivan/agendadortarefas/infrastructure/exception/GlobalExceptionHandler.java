package com.geisivan.agendadortarefas.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Business exceptions
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> handleApiException(ApiException exception) {
        return ResponseEntity
                .status(exception.getStatus())
                .body(exception.getMessage());
    }

    // Security exceptions
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException exception){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Credenciais inválidas");
    }

    // Erros inesperados
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception exception){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno no servidor");
    }
}
