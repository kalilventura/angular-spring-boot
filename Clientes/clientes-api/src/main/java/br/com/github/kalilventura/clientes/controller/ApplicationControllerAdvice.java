package br.com.github.kalilventura.clientes.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import br.com.github.kalilventura.clientes.controller.exception.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    /*
     * Todos os erros do tipo MethodArgumentNotValidException serão retornados desse
     * controller
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidationErrors(MethodArgumentNotValidException notValidException) {
        final BindingResult bindingResult = notValidException.getBindingResult();

        List<String> messages = bindingResult.getAllErrors().stream()
                .map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList());

        return new ApiErrors(messages);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiErrors> handleResponseStatusException(ResponseStatusException exception) {
        String errorMessage = exception.getMessage();
        HttpStatus statusCode = exception.getStatus();
        ApiErrors errors = new ApiErrors(errorMessage);
        return new ResponseEntity<>(errors, statusCode);
    }
}