package com.rodrigo.moneyapi.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class MoneyExceptionHandler  extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

   /* @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handlerHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String formatoInvalido = messageSource.getMessage("formato.invalido", null, LocaleContextHolder.getLocale());
        ValidationErrorDetails veDetails = ValidationErrorDetails.Builder.newBuilder().timestamp(LocalDate.now())
                .title("Error")
                .status(HttpStatus.BAD_REQUEST.value())
                .detail(formatoInvalido)
                .developerMessage(ex.getCause().toString())
                .build();
        return new ResponseEntity<>(veDetails, HttpStatus.BAD_REQUEST);
    }*/

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        String mensagemUsuario = messageSource.getMessage("formato.invalido", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        List<ErroResposta> erros = Arrays.asList(new ErroResposta(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<ErroResposta> erros = criarListaDeErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    private List<ErroResposta> criarListaDeErros(BindingResult bindingResult) {
        String mensagemUsuario = bindingResult.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        String mensagemDesenvolvedor = bindingResult.getFieldErrors().stream()
                .map(FieldError::toString)
                .collect(Collectors.joining(", "));

        List<ErroResposta> erros = new ArrayList<>();
        erros.add(new ErroResposta(mensagemUsuario, mensagemDesenvolvedor));
        return erros;
    }





















}
