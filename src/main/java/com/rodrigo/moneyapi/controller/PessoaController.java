package com.rodrigo.moneyapi.controller;

import com.rodrigo.moneyapi.model.Pessoa;
import com.rodrigo.moneyapi.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("pessoas")
public class PessoaController {

    private PessoaService service;

    @Autowired
    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> buscar() {
        return ResponseEntity.ok().body(service.buscarPessoas());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPorCodigo(@PathVariable Long codigo) {
        Optional<Pessoa> pessoa = service.buscarPessoasPorCodigo(codigo);
        if(!pessoa.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(pessoa);
    }

    @PostMapping
    public ResponseEntity<Pessoa> cadastrar(@Valid @RequestBody Pessoa pessoa, UriComponentsBuilder uriBuilder) {
        Pessoa pessoaSalva = service.cadastrar(pessoa);
        URI uri = uriBuilder.path("/pessoas/{codigo}").buildAndExpand(pessoaSalva.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(pessoaSalva);
    }

}
