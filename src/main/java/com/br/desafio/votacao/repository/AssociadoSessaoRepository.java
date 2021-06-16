package com.br.desafio.votacao.repository;

import com.br.desafio.votacao.domain.AssociadoSessao;
import com.br.desafio.votacao.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AssociadoSessaoRepository extends JpaRepository<AssociadoSessao, Long> {

    List<AssociadoSessao> findBySessaoPautaId(Long idPauta);

}
