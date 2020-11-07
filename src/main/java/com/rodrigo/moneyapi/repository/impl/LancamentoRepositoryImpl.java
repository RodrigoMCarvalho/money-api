package com.rodrigo.moneyapi.repository.impl;

import com.rodrigo.moneyapi.model.Lancamento;
import com.rodrigo.moneyapi.repository.filter.LancamentoFilter;
import com.rodrigo.moneyapi.repository.query.LancamentoRepositoryQuery;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Lancamento> filtrar(LancamentoFilter filter) {
        final StringBuilder sb = new StringBuilder();
        final Map<String, Object> params = new HashMap<>();

        sb.append(" SELECT l FROM Lancamento l WHERE 1=1 ");

        if (StringUtils.hasText(filter.getDescricao())) {
            sb.append(" AND l.descricao LIKE :descricao");
            params.put("descricao", "%" + filter.getDescricao() + "%");
        }

        if (filter.getDataVencimentoAte() != null) {
            sb.append(" AND l.descricao LIKE :descricao");
            params.put("descricao", "%" + filter.getDescricao() + "%");
        }

        if (filter.getDataVencimentoDe() != null) {

        }
        Query query = manager.createQuery(sb.toString(), Lancamento.class);
        obterParametros(params, query);
        return query.getResultList();
    }

    private void obterParametros(Map<String, Object> params, Query query) {
        for(Map.Entry<String, Object> param: params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }
    }
}
