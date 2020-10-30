package com.rodrigo.moneyapi.controller;

import com.rodrigo.moneyapi.model.Lancamento;
import com.rodrigo.moneyapi.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    private LancamentoService service;

    @Autowired
    public LancamentoController(LancamentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Lancamento>> buscar() {
        return ResponseEntity.ok().body(service.buscarLancamentos());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Lancamento> buscarPorCodigo(@PathVariable Long codigo) {
        return ResponseEntity.ok().body(service.buscarLancamentoPorCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, UriComponentsBuilder uriBuilder) {
        Lancamento lancamentoSalva = service.cadastrarLancamento(lancamento);
        URI uri = uriBuilder.path("/lancamentos/{codigo}").buildAndExpand(lancamentoSalva.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(lancamentoSalva);
    }
}
