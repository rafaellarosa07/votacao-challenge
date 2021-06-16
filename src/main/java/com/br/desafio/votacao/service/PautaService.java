package com.br.desafio.votacao.service;


import com.br.desafio.votacao.domain.Pauta;
import com.br.desafio.votacao.domain.dto.PautaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PautaService {

    ResponseEntity<?> listarTodos();

    ResponseEntity<?> listarTodosPaginado(Pageable pageable);

    ResponseEntity<?> cadastrar(PautaDTO produto);

    ResponseEntity<?> buscarPorId(Long id) throws Exception;

    ResponseEntity<?> editar(PautaDTO produto);

    ResponseEntity<?> deletar(Long id) throws Exception;

}
