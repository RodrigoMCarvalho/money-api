package com.rodrigo.moneyapi.service.impl;

import com.rodrigo.moneyapi.exception.ResourceNotFoundException;
import com.rodrigo.moneyapi.model.Pessoa;
import com.rodrigo.moneyapi.repository.PessoaRepository;
import com.rodrigo.moneyapi.service.PessoaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {

    private PessoaRepository repository;

    @Autowired
    public PessoaServiceImpl(PessoaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Pessoa cadastrar(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    @Override
    public List<Pessoa> buscarPessoas() {
        return repository.findAll();
    }

    @Override
    public Pessoa buscarPessoasPorCodigo(Long codigo) {
        Optional<Pessoa> pessoa = repository.findById(codigo);
        return pessoa.orElseThrow(() -> new EmptyResultDataAccessException(1)); //new ResourceNotFoundException("Não foi possível localizar pessoa com o código: " + codigo));
    }

    @Override
    @Transactional
    public void remover(Long codigo) {
        Pessoa pessoa = buscarPessoasPorCodigo(codigo);
        repository.deleteById(pessoa.getCodigo());
    }

    @Override
    public Pessoa atualizar(Long codigo, Pessoa pessoa) {
        Pessoa pessoaSalva = buscarPessoasPorCodigo(codigo);
        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
        return repository.save(pessoaSalva);
    }

    @Override
    public Pessoa atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
        Pessoa pessoaSalva = buscarPessoasPorCodigo(codigo);
        pessoaSalva.setAtivo(ativo);
        return repository.save(pessoaSalva);
    }



















}
