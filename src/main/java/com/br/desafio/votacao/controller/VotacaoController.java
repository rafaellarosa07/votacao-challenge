package com.br.desafio.votacao.controller;

import com.br.desafio.votacao.domain.dto.PautaDTO;
import com.br.desafio.votacao.domain.dto.VotacaoDTO;
import com.br.desafio.votacao.service.PautaService;
import com.br.desafio.votacao.service.VotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/votacao")
public class VotacaoController {

    @Autowired
    private VotacaoService votacaoService;

    @PostMapping
    public ResponseEntity<?> votar(@RequestBody VotacaoDTO voto) {
        return votacaoService.votar(voto);
    }

    @GetMapping("/idPauta")
    public ResponseEntity<?> resultadoVotacaoPauta(@PathVariable Long idPauta){
        return votacaoService.resultadoVotacaoPauta(idPauta);
    }



}
