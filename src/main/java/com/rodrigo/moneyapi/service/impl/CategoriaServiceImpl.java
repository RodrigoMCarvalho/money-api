package com.rodrigo.moneyapi.service.impl;

import com.rodrigo.moneyapi.model.Categoria;
import com.rodrigo.moneyapi.repository.CategoriaRepository;
import com.rodrigo.moneyapi.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private CategoriaRepository repository;

    @Autowired
    public CategoriaServiceImpl(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Categoria> buscarCategorias() {
        return repository.findAll();
    }

    @Override
    public Optional<Categoria> buscarCategoriaPorCodigo(Long codigo) {
        Optional<Categoria> categoria = repository.findById(codigo);
        return categoria;
    }

    @Override
    public Categoria criarCategoria(Categoria categoria) {
        return repository.save(categoria);
    }
}
