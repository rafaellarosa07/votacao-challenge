package com.br.desafio.votacao.service.impl;

import com.br.desafio.votacao.domain.ItemPauta;
import com.br.desafio.votacao.domain.Pauta;
import com.br.desafio.votacao.domain.dto.ItemPautaDTO;
import com.br.desafio.votacao.domain.dto.Mensagem;
import com.br.desafio.votacao.domain.dto.PautaDTO;
import com.br.desafio.votacao.repository.ItemPautaRepository;
import com.br.desafio.votacao.repository.PautaRepository;
import com.br.desafio.votacao.service.ItemPautaService;
import com.br.desafio.votacao.service.PautaService;
import com.br.desafio.votacao.util.ConvertModelToDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ItemPautaServiceImpl extends ConvertModelToDTO implements ItemPautaService {

    private final ItemPautaRepository itemPautaRepository;

    public ItemPautaServiceImpl(ItemPautaRepository itemPautaRepository) {
        this.itemPautaRepository = itemPautaRepository;
    }


    @Override
    public ResponseEntity<?> listarTodosPaginado(Pageable pageable) {
        return ResponseEntity.ok(itemPautaRepository.findAll(pageable));
    }

    @Override
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(itemPautaRepository.findAll());
    }

    @Override
    public ResponseEntity<?> cadastrar(ItemPautaDTO itemPauta) {
        try {
            itemPautaRepository.save(itemPauta.paraEntidade());


            return new ResponseEntity<>(new Mensagem("Item Pauta cadastrada com sucesso!", 0L, "success", true),
                    HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Mensagem("Ocorreu um erro ao tentar cadastrar a item Pauta!", 0L, "error", true),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> cadastrarList(List<ItemPautaDTO> itensPautas) {
        try {

            itemPautaRepository.saveAll(itensPautas.stream().map(item -> super.toModel(item, ItemPauta.class)).collect(Collectors.toList()));


            return new ResponseEntity<>(new Mensagem("Item Pauta cadastrada com sucesso!", 0L, "success", true),
                    HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Mensagem("Ocorreu um erro ao tentar cadastrar a item Pauta!", 0L, "error", true),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ItemPauta> buscarPorId(Long id) throws Exception {
        return ResponseEntity.ok(itemPautaRepository.findById(id).orElseThrow(() ->
                new Exception("Não foi possivel encontrar a item Pauta de ID: " + id)));
    }

    @Override
    public ResponseEntity<?> editar(ItemPautaDTO itemPauta) {
        try {
            itemPautaRepository.save(itemPauta.paraEntidade());

            return new ResponseEntity<>(new Mensagem("Item Pauta editada com sucesso!", 0L, "success", true),
                    HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Mensagem("Ocorreu um erro ao tentar editar a item Pauta!", 0L, "error", true),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> deletar(Long id) throws Exception {
        var itemPauta = itemPautaRepository.findById(id).orElseThrow(() -> new Exception("Não existe uma itemPauta para o ID: " + id));
        try {
            itemPautaRepository.delete(itemPauta);

            return new ResponseEntity<>(new Mensagem("Item Pauta deletada com sucesso!", 0L, "success", true),
                    HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Mensagem("Ocorreu um erro ao tentar deletar a item Pauta!", 0L, "error", true),
                    HttpStatus.BAD_REQUEST);
        }
    }




}
