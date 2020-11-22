package com.rodrigo.moneyapi.repository.impl;

import com.rodrigo.moneyapi.model.Pessoa;
import com.rodrigo.moneyapi.repository.filter.LancamentoFilter;
import com.rodrigo.moneyapi.repository.filter.PessoaFilter;
import com.rodrigo.moneyapi.repository.query.PessoaRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

public class PessoaRepositoryImpl implements PessoaRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Pessoa> filtrar(PessoaFilter filter, Pageable pageable) {
        final StringBuilder sb = new StringBuilder();
        final Map<String, Object> params = new HashMap<>();

        sb.append(" SELECT p FROM Pessoa p WHERE 1=1 ");

        if (StringUtils.hasText(filter.getNome())) {
            sb.append(" AND p.nome LIKE :nome");
            params.put("nome", "%" + filter.getNome() + "%");
        }

        Query query = manager.createQuery(sb.toString(), Pessoa.class);

        obterParametros(params, query);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(filter));
    }

    private Long total(PessoaFilter filter) {
        String consulta = "SELECT COUNT(p) FROM Pessoa p";
        Query query = manager.createQuery(consulta, Long.class);
        return (Long) query.getSingleResult();
    }

    private void adicionarRestricoesDePaginacao(Query query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorpagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorpagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorpagina);
    }

    private void obterParametros(Map<String, Object> params, Query query) {
        for(Map.Entry<String, Object> param: params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }
    }
}
