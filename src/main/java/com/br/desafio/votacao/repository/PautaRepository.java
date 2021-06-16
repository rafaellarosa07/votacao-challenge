package com.br.desafio.votacao.repository;

import com.br.desafio.votacao.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
