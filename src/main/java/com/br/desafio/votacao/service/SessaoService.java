package com.br.desafio.votacao.service;


import com.br.desafio.votacao.domain.Sessao;
import com.br.desafio.votacao.domain.dto.SessaoDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SessaoService {

    ResponseEntity<?> listarTodos();

    ResponseEntity<?> listarTodosPaginado(Pageable pageable);

    ResponseEntity<?> abrir(SessaoDTO produto);

    Sessao getSessaoAbertaIdPauta(Long idPauta);


}
