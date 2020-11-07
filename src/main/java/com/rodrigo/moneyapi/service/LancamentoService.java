package com.rodrigo.moneyapi.service;

import com.rodrigo.moneyapi.model.Lancamento;
import com.rodrigo.moneyapi.repository.filter.LancamentoFilter;

import java.util.List;

public interface LancamentoService {

    List<Lancamento> buscarLancamentos();
    List<Lancamento> buscarLancamentosPorFiltro(LancamentoFilter filter);
    Lancamento buscarLancamentoPorCodigo(Long codigo);
    Lancamento cadastrarLancamento(Lancamento lancamento);
}
