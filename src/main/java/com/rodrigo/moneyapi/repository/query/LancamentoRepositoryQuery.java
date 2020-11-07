package com.rodrigo.moneyapi.repository.query;

import com.rodrigo.moneyapi.model.Lancamento;
import com.rodrigo.moneyapi.repository.filter.LancamentoFilter;

import java.util.List;

public interface LancamentoRepositoryQuery {

    List<Lancamento> filtrar(LancamentoFilter filter);
}
