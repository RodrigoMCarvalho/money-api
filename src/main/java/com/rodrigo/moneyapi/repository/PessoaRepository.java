package com.rodrigo.moneyapi.repository;

import com.rodrigo.moneyapi.model.Pessoa;
import com.rodrigo.moneyapi.repository.impl.PessoaRepositoryImpl;
import com.rodrigo.moneyapi.repository.query.PessoaRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryQuery {
}
