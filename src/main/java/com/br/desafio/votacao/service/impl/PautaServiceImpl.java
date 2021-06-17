package com.br.desafio.votacao.service.impl;

import com.br.desafio.votacao.domain.ItemPauta;
import com.br.desafio.votacao.domain.Pauta;
import com.br.desafio.votacao.domain.dto.ItemPautaDTO;
import com.br.desafio.votacao.domain.dto.Mensagem;
import com.br.desafio.votacao.domain.dto.PautaDTO;
import com.br.desafio.votacao.repository.PautaRepository;
import com.br.desafio.votacao.service.ItemPautaService;
import com.br.desafio.votacao.service.PautaService;
import com.br.desafio.votacao.util.ConvertModelToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PautaServiceImpl extends ConvertModelToDTO implements PautaService {

    private final PautaRepository pautaRepository;

    @Autowired
    private ItemPautaService itemPautaService;

    public PautaServiceImpl(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }


    @Override
    public ResponseEntity<?> listarTodosPaginado(Pageable pageable) {
        return ResponseEntity.ok(pautaRepository.findAll(pageable));
    }

    @Override
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(pautaRepository.findAll());
    }

    @Override
    public ResponseEntity<?> cadastrar(PautaDTO pautaDTO) {
        try {
            Pauta pauta = pautaDTO.paraEntidade();
            List<ItemPauta> itensPautas = pautaDTO.getItensPauta().stream().map(iPauta -> {
                var itemPauta =  super.toModel(iPauta, ItemPauta.class);
                itemPauta.setPauta(pauta);
                return itemPauta;
            }).collect(Collectors.toList());
            pautaRepository.save(pauta);
            itemPautaService.cadastrarList(itensPautas);

            return new ResponseEntity<>(new Mensagem("Pauta cadastrada com sucesso!", 0L, "success", true),
                    HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Mensagem("Ocorreu um erro ao tentar cadastrar a pauta!", 0L, "error", true),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Pauta> buscarPorId(Long id) throws Exception {
        return ResponseEntity.ok(pautaRepository.findById(id).orElseThrow(() ->
                new Exception("Não foi possivel encontrar a pauta de ID: " + id)));
    }

    @Override
    public ResponseEntity<?> editar(PautaDTO pauta) {
        try {
            pautaRepository.save(pauta.paraEntidade());

            return new ResponseEntity<>(new Mensagem("Pauta editada com sucesso!", 0L, "success", true),
                    HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Mensagem("Ocorreu um erro ao tentar editar a pauta!", 0L, "error", true),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> deletar(Long id) throws Exception {
        var pauta = pautaRepository.findById(id).orElseThrow(() -> new Exception("Não existe uma pauta para o ID: " + id));
        try {
            pautaRepository.delete(pauta);

            return new ResponseEntity<>(new Mensagem("Pauta deletada com sucesso!", 0L, "success", true),
                    HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Mensagem("Ocorreu um erro ao tentar deletar a pauta!", 0L, "error", true),
                    HttpStatus.BAD_REQUEST);
        }
    }




}
