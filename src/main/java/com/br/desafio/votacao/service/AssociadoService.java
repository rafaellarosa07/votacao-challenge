package com.br.desafio.votacao.service;


import com.br.desafio.votacao.domain.dto.AssociadoDTO;
import com.br.desafio.votacao.domain.dto.PautaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AssociadoService {

    ResponseEntity<?> listarTodos();

    ResponseEntity<?> listarTodosPaginado(Pageable pageable);

    ResponseEntity<?> cadastrar(AssociadoDTO produto);

    ResponseEntity<?> buscarPorId(Long id) throws Exception;

    ResponseEntity<?> editar(AssociadoDTO produto);

    ResponseEntity<?> deletar(Long id) throws Exception;

}
