package com.br.desafio.votacao.repository;

import com.br.desafio.votacao.domain.Associado;
import com.br.desafio.votacao.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AssociadoRepository extends JpaRepository<Associado, Long> {
}
