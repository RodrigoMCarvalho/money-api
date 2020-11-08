package com.rodrigo.moneyapi.service.impl;

import com.rodrigo.moneyapi.exception.PessoaInexistenteOuInativaException;
import com.rodrigo.moneyapi.model.Lancamento;
import com.rodrigo.moneyapi.model.Pessoa;
import com.rodrigo.moneyapi.repository.LancamentoRepository;
import com.rodrigo.moneyapi.repository.PessoaRepository;
import com.rodrigo.moneyapi.repository.filter.LancamentoFilter;
import com.rodrigo.moneyapi.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    private LancamentoRepository lancamentoRepository;
    private PessoaRepository pessoaRepository;

    @Autowired
    public LancamentoServiceImpl(LancamentoRepository lancamentoRepository, PessoaRepository pessoaRepository) {
        this.lancamentoRepository = lancamentoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public List<Lancamento> buscarLancamentos() {
        return lancamentoRepository.findAll();
    }

    @Override
    public Page<Lancamento> buscarLancamentosPorFiltro(LancamentoFilter filter, Pageable pageable) {
        return lancamentoRepository.filtrar(filter, pageable);
    }

    @Override
    public Lancamento buscarLancamentoPorCodigo(Long codigo) {
        return lancamentoRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    @Override
    public Lancamento cadastrarLancamento(Lancamento lancamento) {
        Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo()).get();
        if(pessoa == null || pessoa.isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }
        return lancamentoRepository.save(lancamento);
    }

    @Override
    public void excluirLancamento(Long id) {
        buscarLancamentoPorCodigo(id);
        lancamentoRepository.deleteById(id);
    }


}
