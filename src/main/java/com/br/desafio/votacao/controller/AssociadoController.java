package com.br.desafio.votacao.controller;

import com.br.desafio.votacao.domain.dto.AssociadoDTO;
import com.br.desafio.votacao.service.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/associado")
public class AssociadoController {

    @Autowired
    private AssociadoService associadoService;

    @GetMapping()
    public ResponseEntity<?> listarTodos() {
        return associadoService.listarTodos();
    }

    @GetMapping("/page")
    public ResponseEntity<?> listarTodos(@PageableDefault(size = 20,
            sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {
        return associadoService.listarTodosPaginado(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) throws Exception {
        return associadoService.buscarPorId(id);
    }


    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody AssociadoDTO associado) {
        return associadoService.cadastrar(associado);
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestBody AssociadoDTO associado) {
        return associadoService.editar(associado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) throws Exception {
        return associadoService.deletar(id);
    }


}
