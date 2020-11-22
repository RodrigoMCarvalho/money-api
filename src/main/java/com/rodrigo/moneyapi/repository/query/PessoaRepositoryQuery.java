package com.rodrigo.moneyapi.repository.query;

import com.rodrigo.moneyapi.model.Pessoa;
import com.rodrigo.moneyapi.repository.filter.PessoaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaRepositoryQuery {

    Page<Pessoa> filtrar(PessoaFilter filter, Pageable pageable);
}
