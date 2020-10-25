package com.rodrigo.moneyapi.service;

import com.rodrigo.moneyapi.model.Pessoa;

import java.util.List;
import java.util.Optional;

public interface PessoaService {

    Pessoa cadastrar(Pessoa pessoa);
    List<Pessoa> buscarPessoas();
    Optional<Pessoa> buscarPessoasPorCodigo(Long codigo);
}
