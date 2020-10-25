package com.rodrigo.moneyapi.service.impl;

import com.rodrigo.moneyapi.model.Pessoa;
import com.rodrigo.moneyapi.repository.PessoaRepository;
import com.rodrigo.moneyapi.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Optional<Pessoa> buscarPessoasPorCodigo(Long codigo) {
        return repository.findById(codigo);
    }
}
