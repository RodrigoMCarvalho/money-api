package com.rodrigo.moneyapi.service;

import com.rodrigo.moneyapi.model.Pessoa;

import java.util.List;

public interface PessoaService {

    Pessoa cadastrar(Pessoa pessoa);
    List<Pessoa> buscarPessoas();
    Pessoa buscarPessoasPorCodigo(Long codigo);
    void remover(Long codigo);
    Pessoa atualizar(Long codigo, Pessoa pessoa);
    void atualizarPropriedadeAtivo(Long codigo, Boolean ativo);
}
