package com.br.desafio.votacao.repository;

import com.br.desafio.votacao.domain.Pauta;
import com.br.desafio.votacao.domain.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;


public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    @Query(value = "SELECT s from Sessao s where s.pauta.id = :idPauta AND :dataHoje between s.dataInicio and s.dataFim")
    Sessao findByPautIdAndData(Long idPauta, LocalDateTime dataHoje);


}
