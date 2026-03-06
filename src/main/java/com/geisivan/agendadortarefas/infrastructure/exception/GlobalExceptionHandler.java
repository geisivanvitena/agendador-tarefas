package com.geisivan.agendadortarefas.infrastructure.exception;

import com.geisivan.agendadortarefas.infrastructure.exception.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Business exceptions
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponseDTO> handleApiException(ApiException exception,
                                                     HttpServletRequest request) {
        ErrorResponseDTO error = buildError(
                exception.getStatus(),
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity
                .status(exception.getStatus())
                .body(error);
    }

    // Security exceptions
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponseDTO> handleAuthenticationException(AuthenticationException exception,
                                                                HttpServletRequest request){
        ErrorResponseDTO error = buildError(
                HttpStatus.UNAUTHORIZED,
                "Credenciais inválidas",
                request.getRequestURI()
        );
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(error);
    }

    // Erros inesperados
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception exception,
                                                         HttpServletRequest request){
        ErrorResponseDTO error = buildError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro interno no servidor",
                request.getRequestURI()
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }

    private ErrorResponseDTO buildError(HttpStatus status, String message, String path) {
        return ErrorResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(path)
                .build();
    }
}
