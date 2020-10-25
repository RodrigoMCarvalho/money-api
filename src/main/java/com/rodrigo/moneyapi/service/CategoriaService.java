package com.rodrigo.moneyapi.service;

import com.rodrigo.moneyapi.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    List<Categoria> buscarCategorias();
    Optional<Categoria> buscarCategoriaPorCodigo(Long codigo);
    Categoria cadastracategoria(Categoria categoria);
}
