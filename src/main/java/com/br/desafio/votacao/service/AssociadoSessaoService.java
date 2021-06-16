package com.br.desafio.votacao.service;


import com.br.desafio.votacao.domain.Associado;
import com.br.desafio.votacao.domain.AssociadoSessao;
import com.br.desafio.votacao.domain.dto.AssociadoDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AssociadoSessaoService {

    ResponseEntity<?> cadastrar(AssociadoSessao produto);

    List<AssociadoSessao> buscarByIdPauta(Long idPauta);

}
