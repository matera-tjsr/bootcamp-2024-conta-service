package com.bootcamp.conta.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class SaldoInsuficienteExisteException extends RuntimeException {

    public SaldoInsuficienteExisteException(String message) {
        super(message);
    }
}