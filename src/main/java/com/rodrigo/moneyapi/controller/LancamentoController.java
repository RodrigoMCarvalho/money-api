package com.rodrigo.moneyapi.controller;

import com.rodrigo.moneyapi.model.Lancamento;
import com.rodrigo.moneyapi.repository.filter.LancamentoFilter;
import com.rodrigo.moneyapi.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /**
     * Realiza busca de lançamentos com a opção de filtro e paginação
     * http://localhost:8080/lancamentos?descricao=conta&dataVencimentoDe=2020-01-01&size=3&page=0
     * @param filter
     * @param pageable
     * @return lista de lançamentos
     */
    @GetMapping
    public ResponseEntity<Page<Lancamento>> buscar(LancamentoFilter filter, Pageable pageable) {
        return ResponseEntity.ok().body(service.buscarLancamentosPorFiltro(filter, pageable));
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Lancamento> deletar(@PathVariable Long id) {
        service.excluirLancamento(id);
        return ResponseEntity.noContent().build();
    }
}
