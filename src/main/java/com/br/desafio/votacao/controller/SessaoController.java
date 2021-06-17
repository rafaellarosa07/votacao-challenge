package com.br.desafio.votacao.controller;

import com.br.desafio.votacao.domain.dto.SessaoDTO;
import com.br.desafio.votacao.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/sessao")
public class SessaoController {

    @Autowired
    private SessaoService sessaoService;

    @GetMapping()
    public ResponseEntity<?> listarTodos() {
        return sessaoService.listarTodos();
    }

    @GetMapping("/page")
    public ResponseEntity<?> listarTodos(@PageableDefault(size = 20,
            sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {
        return sessaoService.listarTodosPaginado(pageable);
    }

    @PostMapping
    public ResponseEntity<?> abrir(@RequestBody SessaoDTO sessao) {
        return sessaoService.abrir(sessao);
    }


}
