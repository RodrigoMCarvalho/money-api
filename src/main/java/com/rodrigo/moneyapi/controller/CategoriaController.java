package com.rodrigo.moneyapi.controller;

import com.rodrigo.moneyapi.model.Categoria;
import com.rodrigo.moneyapi.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

    private CategoriaService service;

    @Autowired
    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> buscar() {
     return ResponseEntity.ok().body(service.buscarCategorias());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPorCodigo(@PathVariable Long codigo) {
        Optional<Categoria> categoria = service.buscarCategoriaPorCodigo(codigo);
        if(!categoria.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(categoria.get());
    }

    @PostMapping
    public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, UriComponentsBuilder uriBuilder) {
        Categoria categoriaSalva = service.cadastracategoria(categoria);
        URI uri = uriBuilder.path("/categorias/{codigo}").buildAndExpand(categoriaSalva.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(categoriaSalva);
    }


}
