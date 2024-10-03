package com.bootcamp.conta.service.config;

import com.bootcamp.conta.service.exception.ContaExistenteException;
import com.bootcamp.conta.service.exception.ContaNaoExisteException;
import com.bootcamp.conta.service.exception.SaldoInsuficienteExisteException;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ContaExistenteException.class)
    private ProblemDetail handlerContaExistente(ContaExistenteException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problemDetail.setTitle("Conflict");
        problemDetail.setType(URI.create("http://localhost/9000/doc/conta-existente"));
        return problemDetail;
    }

    @ExceptionHandler(ContaNaoExisteException.class)
    private ProblemDetail handlerContaNaoExistente(ContaNaoExisteException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Not Found");
        problemDetail.setType(URI.create("http://localhost/9000/document/conta-nao-existe"));
        return problemDetail;
    }

    @ExceptionHandler(SaldoInsuficienteExisteException.class)
    private ProblemDetail handlerSaldoInsuficiente(SaldoInsuficienteExisteException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        problemDetail.setTitle("Saldo Insuficiente");
        problemDetail.setType(URI.create("http://localhost/9000/document/saldo-insuficiente"));
        return problemDetail;
    }

}
