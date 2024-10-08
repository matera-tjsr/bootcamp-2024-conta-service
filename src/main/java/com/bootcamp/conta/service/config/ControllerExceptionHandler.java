package com.bootcamp.conta.service.config;

import com.bootcamp.conta.service.exception.ContaExistenteException;
import com.bootcamp.conta.service.exception.ContaNaoExisteException;
import com.bootcamp.conta.service.exception.ErroCadastroChaveBacenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ContaExistenteException.class)
    private ProblemDetail exceptionContaExistente(ContaExistenteException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problemDetail.setTitle("Conflict");
        problemDetail.setType(URI.create("http://localhost/9000/doc/conta-existente"));
        return problemDetail;
    }

    @ExceptionHandler(ContaNaoExisteException.class)
    private ProblemDetail exceptionContaNaoExistente(ContaNaoExisteException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Not Found");
        problemDetail.setType(URI.create("http://localhost/9000/doc/conta-nao-existe"));
        return problemDetail;
    }

    @ExceptionHandler(ErroCadastroChaveBacenException.class)
    private ProblemDetail exceptionErroCadastroChaveBacen(ErroCadastroChaveBacenException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("Error Bacen");
        problemDetail.setType(URI.create("http://localhost/9000/doc/error-bacen"));
        return problemDetail;
    }

}
