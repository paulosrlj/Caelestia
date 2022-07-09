package com.ifpb.caelestiabackend.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.ifpb.caelestiabackend.domain.entities.HttpErrorMessage;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(MissingParamException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<HttpErrorMessage> handleMissingParamException(MissingParamException ex, WebRequest request) {
//        HttpErrorMessage message = new HttpErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
//    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
                                                               HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorDetails = "JSON inválido " + exception.getMessage();

        if (exception.getCause() instanceof InvalidFormatException ifx) {
            if (ifx.getTargetType() != null && ifx.getTargetType().isEnum()) {
                errorDetails = String.format("Enum inválido: '%s' para o campo: '%s'. " +
                                "O valor deve ser um destes: %s.",
                        ifx.getValue(), ifx.getPath().get(ifx.getPath().size() - 1).getFieldName(),
                        Arrays.toString(ifx.getTargetType().getEnumConstants()));
            }
        }

        List<String> errors = Collections.singletonList(errorDetails);

        HttpErrorMessage message = new HttpErrorMessage(HttpStatus.BAD_REQUEST, "Erro de validação", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

        HttpErrorMessage message = new HttpErrorMessage(HttpStatus.BAD_REQUEST, "Erro de validação", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<HttpErrorMessage> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
