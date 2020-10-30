package com.rodrigo.moneyapi.repository;

import com.rodrigo.moneyapi.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoRepository  extends JpaRepository<Lancamento, Long> {
}
