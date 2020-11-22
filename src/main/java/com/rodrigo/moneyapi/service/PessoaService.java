package com.rodrigo.moneyapi.service;

import com.rodrigo.moneyapi.model.Pessoa;
import com.rodrigo.moneyapi.repository.filter.PessoaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PessoaService {

    Pessoa cadastrar(Pessoa pessoa);
    List<Pessoa> buscarPessoas();
    Page<Pessoa> buscarPessoasPorFiltro(PessoaFilter filter, Pageable pageable);
    Pessoa buscarPessoasPorCodigo(Long codigo);
    void remover(Long codigo);
    Pessoa atualizar(Long codigo, Pessoa pessoa);
    Pessoa atualizarPropriedadeAtivo(Long codigo, Boolean ativo);
}
