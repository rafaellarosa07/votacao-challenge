package com.br.desafio.votacao.service.impl;

import com.br.desafio.votacao.domain.Sessao;
import com.br.desafio.votacao.domain.dto.Mensagem;
import com.br.desafio.votacao.domain.dto.SessaoDTO;
import com.br.desafio.votacao.repository.SessaoRepository;
import com.br.desafio.votacao.service.SessaoService;
import com.br.desafio.votacao.util.ConvertModelToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;


@Service
public class SessaoServiceImpl extends ConvertModelToDTO implements SessaoService {

    @Autowired
    private  SessaoRepository sessaoRepository;

    @Override
    public ResponseEntity<?> listarTodosPaginado(Pageable pageable) {
        return ResponseEntity.ok(sessaoRepository.findAll(pageable));
    }

    @Override
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(sessaoRepository.findAll());
    }

    @Override
    public ResponseEntity<?> abrir(SessaoDTO sessao) {
        try {
            var sessaoSave = super.toModel(sessao, Sessao.class);
            if(ObjectUtils.isEmpty(sessao.getDataFim())){
                sessaoSave.setDataFim(sessao.getDataInicio().plusMinutes(1));
            }
            sessaoRepository.save(sessaoSave);

            return new ResponseEntity<>(new Mensagem("Sessao cadastrada com sucesso!", 0L, "success", true),
                    HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Mensagem("Ocorreu um erro ao tentar cadastrar a sessao!", 0L, "error", true),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Sessao getSessaoAbertaIdPauta(Long idPauta) {
        return sessaoRepository.findByPauta_IdAndDataInicioBeforeAndDataFimAfter(idPauta, LocalDateTime.now(), LocalDateTime.now());
    }

}
