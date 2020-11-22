package com.rodrigo.moneyapi.repository.impl;

import com.rodrigo.moneyapi.model.Lancamento;
import com.rodrigo.moneyapi.repository.filter.LancamentoFilter;
import com.rodrigo.moneyapi.repository.projection.ResumoLancamento;
import com.rodrigo.moneyapi.repository.query.LancamentoRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable) {
        final StringBuilder sb = new StringBuilder();
        final Map<String, Object> params = new HashMap<>();

        sb.append(" SELECT l FROM Lancamento l WHERE 1=1 ");

        if (StringUtils.hasText(filter.getDescricao())) {
            sb.append(" AND l.descricao LIKE :descricao");
            params.put("descricao", "%" + filter.getDescricao() + "%");
        }

        if (filter.getDataVencimentoAte() != null) {
            sb.append(" AND l.dataVencimento <= :dataVencimentoAte");
            params.put("dataVencimentoAte", filter.getDataVencimentoAte());
        }

        if (filter.getDataVencimentoDe() != null) {
            sb.append(" AND l.dataVencimento >= :dataVencimentoDe");
            params.put("dataVencimentoDe", filter.getDataVencimentoDe());
        }
        Query query = manager.createQuery(sb.toString(), Lancamento.class);

        obterParametros(params, query);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(filter));
    }

    @Override
    public Page<ResumoLancamento> resumir(LancamentoFilter filter, Pageable pageable) {
        Page<Lancamento> lancamentos = filtrar(filter, pageable);
        return new PageImpl<>(lancamentos.stream()
                .map(ResumoLancamento::new)
                .collect(Collectors.toList()));
    }

    private Long total(LancamentoFilter filter) {
        String consulta = "SELECT COUNT(l) FROM Lancamento l";
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
