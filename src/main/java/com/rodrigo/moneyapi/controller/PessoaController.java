package com.rodrigo.moneyapi.controller;

import com.rodrigo.moneyapi.model.Pessoa;
import com.rodrigo.moneyapi.repository.filter.LancamentoFilter;
import com.rodrigo.moneyapi.repository.filter.PessoaFilter;
import com.rodrigo.moneyapi.service.PessoaService;
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
@RequestMapping("pessoas")
public class PessoaController {

    private PessoaService service;

    @Autowired
    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pessoa>> buscar() {
        return ResponseEntity.ok().body(service.buscarPessoas());
    }

    /**
     * Realiza busca de Pessoas com a opção de filtro e paginação
     * http://localhost:8080/pessoas?nome=maria&size=1&page=0
     * @param filter
     * @param pageable
     * @return lista de pessoas
     */
    @GetMapping
    public ResponseEntity<Page<Pessoa>> buscarPorFiltro(PessoaFilter filter, Pageable pageable) {
        return ResponseEntity.ok().body(service.buscarPessoasPorFiltro(filter,pageable));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPorCodigo(@PathVariable Long codigo) {
        return ResponseEntity.ok().body(service.buscarPessoasPorCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<Pessoa> cadastrar(@Valid @RequestBody Pessoa pessoa, UriComponentsBuilder uriBuilder) {
        Pessoa pessoaSalva = service.cadastrar(pessoa);
        URI uri = uriBuilder.path("/pessoas/{codigo}").buildAndExpand(pessoaSalva.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(pessoaSalva);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Pessoa> remover(@PathVariable Long codigo) {
        service.remover(codigo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
        return ResponseEntity.ok().body(service.atualizar(codigo, pessoa));
    }

    @PutMapping("/{codigo}/ativo")
    public ResponseEntity<Pessoa> atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
        return ResponseEntity.ok().body(service.atualizarPropriedadeAtivo(codigo, ativo));
    }





















}
