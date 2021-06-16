package com.br.desafio.votacao.service;


import com.br.desafio.votacao.domain.dto.ItemPautaDTO;
import com.br.desafio.votacao.domain.dto.PautaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ItemPautaService {

    ResponseEntity<?> listarTodos();

    ResponseEntity<?> listarTodosPaginado(Pageable pageable);

    ResponseEntity<?> cadastrar(ItemPautaDTO produto);

    ResponseEntity<?> cadastrarList(List<ItemPautaDTO> produto);

    ResponseEntity<?> buscarPorId(Long id) throws Exception;

    ResponseEntity<?> editar(ItemPautaDTO produto);

    ResponseEntity<?> deletar(Long id) throws Exception;

}
