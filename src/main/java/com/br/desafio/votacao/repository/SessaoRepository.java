package com.br.desafio.votacao.repository;

import com.br.desafio.votacao.domain.Pauta;
import com.br.desafio.votacao.domain.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;


public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    Sessao findByPauta_IdAndDataInicioBeforeAndDataFimAfter(Long idPauta, LocalDateTime dataHoje, LocalDateTime dataHoje2);


}
