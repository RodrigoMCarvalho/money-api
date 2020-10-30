package com.rodrigo.moneyapi.service;

import com.rodrigo.moneyapi.model.Lancamento;

import java.util.List;

public interface LancamentoService {

    List<Lancamento> buscarLancamentos();
    Lancamento buscarLancamentoPorCodigo(Long codigo);
    Lancamento cadastrarLancamento(Lancamento lancamento);
}
