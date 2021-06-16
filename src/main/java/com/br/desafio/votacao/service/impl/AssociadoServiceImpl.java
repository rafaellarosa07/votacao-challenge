package com.br.desafio.votacao.service.impl;

import com.br.desafio.votacao.domain.Associado;
import com.br.desafio.votacao.domain.dto.Mensagem;
import com.br.desafio.votacao.domain.dto.AssociadoDTO;
import com.br.desafio.votacao.repository.AssociadoRepository;
import com.br.desafio.votacao.service.AssociadoService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class AssociadoServiceImpl implements AssociadoService {

    private final AssociadoRepository associadoRepository;


    public AssociadoServiceImpl(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }


    @Override
    public ResponseEntity<?> listarTodosPaginado(Pageable pageable) {
        return ResponseEntity.ok(associadoRepository.findAll(pageable));
    }

    @Override
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(associadoRepository.findAll());
    }

    @Override
    public ResponseEntity<?> cadastrar(AssociadoDTO associado) {
        try {
            associadoRepository.save(associado.paraEntidade());

            return new ResponseEntity<>(new Mensagem("Associado cadastrada com sucesso!", 0L, "success", true),
                    HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Mensagem("Ocorreu um erro ao tentar cadastrar a associado!", 0L, "error", true),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Associado> buscarPorId(Long id) throws Exception {
        return ResponseEntity.ok(associadoRepository.findById(id).orElseThrow(() ->
                new Exception("Não foi possivel encontrar a associado de ID: " + id)));
    }

    @Override
    public ResponseEntity<?> editar(AssociadoDTO associado) {
        try {
            associadoRepository.save(associado.paraEntidade());

            return new ResponseEntity<>(new Mensagem("Associado editada com sucesso!", 0L, "success", true),
                    HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Mensagem("Ocorreu um erro ao tentar editar a associado!", 0L, "error", true),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> deletar(Long id) throws Exception {
        var associado = associadoRepository.findById(id).orElseThrow(() -> new Exception("Não existe uma associado para o ID: " + id));
        try {
            associadoRepository.delete(associado);

            return new ResponseEntity<>(new Mensagem("Associado deletada com sucesso!", 0L, "success", true),
                    HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Mensagem("Ocorreu um erro ao tentar deletar a associado!", 0L, "error", true),
                    HttpStatus.BAD_REQUEST);
        }
    }




}
