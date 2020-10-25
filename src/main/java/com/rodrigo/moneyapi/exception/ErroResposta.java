package com.rodrigo.moneyapi.exception;

public class ErroResposta {

    private String mensagemUsuario;
    private String mensagemDesenvolvedor;

    public ErroResposta(String mensagemUsuario, String mensagemDesenvolvedor) {
        this.mensagemUsuario = mensagemUsuario;
        this.mensagemDesenvolvedor = mensagemDesenvolvedor;
    }

    public String getMensagemUsuario() {
        return mensagemUsuario;
    }

    public String getMensagemDesenvolvedor() {
        return mensagemDesenvolvedor;
    }

}
