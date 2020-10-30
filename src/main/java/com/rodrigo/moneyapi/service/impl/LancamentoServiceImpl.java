package com.rodrigo.moneyapi.service.impl;

import com.rodrigo.moneyapi.model.Lancamento;
import com.rodrigo.moneyapi.repository.LancamentoRepository;
import com.rodrigo.moneyapi.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    private LancamentoRepository repository;

    @Autowired
    public LancamentoServiceImpl(LancamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Lancamento> buscarLancamentos() {
        return repository.findAll();
    }

    @Override
    public Lancamento buscarLancamentoPorCodigo(Long codigo) {
        return repository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    @Override
    public Lancamento cadastrarLancamento(Lancamento lancamento) {
        return repository.save(lancamento);
    }
}
