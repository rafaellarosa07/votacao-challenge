package com.br.desafio.votacao.service;


import com.br.desafio.votacao.domain.dto.PautaDTO;
import com.br.desafio.votacao.domain.dto.VotacaoDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface VotacaoService {

    ResponseEntity<?> votar(VotacaoDTO votacaoDTO);

    ResponseEntity<?> resultadoVotacaoPauta(Long idPauta);

}
