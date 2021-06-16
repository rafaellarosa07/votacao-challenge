package com.br.desafio.votacao.controller;

import com.br.desafio.votacao.domain.dto.PautaDTO;
import com.br.desafio.votacao.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pauta")
public class PautaController {

    @Autowired
    private PautaService pautaService;

    @GetMapping()
    public ResponseEntity<?> listarTodos() {
        return pautaService.listarTodos();
    }

    @GetMapping("/page")
    public ResponseEntity<?> listarTodos(@PageableDefault(size = 20,
            sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {
        return pautaService.listarTodosPaginado(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) throws Exception {
        return pautaService.buscarPorId(id);
    }


    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody PautaDTO pauta) {
        return pautaService.cadastrar(pauta);
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestBody PautaDTO pauta) {
        return pautaService.editar(pauta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) throws Exception {
        return pautaService.deletar(id);
    }


}
