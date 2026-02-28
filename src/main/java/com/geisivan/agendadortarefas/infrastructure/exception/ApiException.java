package com.geisivan.agendadortarefas.infrastructure.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiException extends RuntimeException {

    private final HttpStatus status;

    protected ApiException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }
    protected ApiException(String message, HttpStatus status, Throwable cause) {
        super(message, cause);
        this.status = status;
    }
    public HttpStatus getStatus(){
        return status;
    }
}
