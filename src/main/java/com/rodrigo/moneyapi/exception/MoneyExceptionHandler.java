package com.rodrigo.moneyapi.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class MoneyExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handlerHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String formatoInvalido = messageSource.getMessage("formato.invalido", null, LocaleContextHolder.getLocale());
        ValidationErrorDetails veDetails = ValidationErrorDetails.Builder.newBuilder().timestamp(LocalDate.now())
                .title("Error")
                .status(HttpStatus.BAD_REQUEST.value())
                .detail(formatoInvalido)
                .developerMessage(ex.getCause().toString())
                .build();
        return new ResponseEntity<>(veDetails, HttpStatus.BAD_REQUEST);
    }


}
