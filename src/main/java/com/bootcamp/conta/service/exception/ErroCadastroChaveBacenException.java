package com.bootcamp.conta.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErroCadastroChaveBacenException extends RuntimeException {
    public ErroCadastroChaveBacenException(String message, Throwable cause) {
        super(message, cause);
    }
}
