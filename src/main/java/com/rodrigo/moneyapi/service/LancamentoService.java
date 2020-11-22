package com.rodrigo.moneyapi.service;

import com.rodrigo.moneyapi.model.Lancamento;
import com.rodrigo.moneyapi.repository.filter.LancamentoFilter;
import com.rodrigo.moneyapi.repository.projection.ResumoLancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LancamentoService {

    List<Lancamento> buscarLancamentos();
    List<ResumoLancamento> buscarResumoLancamento(Lancamento lancamento);
    Page<Lancamento> buscarLancamentosPorFiltro(LancamentoFilter filter, Pageable pageable);
    Page<ResumoLancamento> buscarResumoLancamentosPorFiltro(LancamentoFilter filter, Pageable pageable);
    Lancamento buscarLancamentoPorCodigo(Long codigo);
    Lancamento cadastrarLancamento(Lancamento lancamento);
    void excluirLancamento(Long id);
}
