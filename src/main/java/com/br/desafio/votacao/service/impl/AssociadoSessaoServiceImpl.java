package com.br.desafio.votacao.service.impl;

import com.br.desafio.votacao.domain.Associado;
import com.br.desafio.votacao.domain.AssociadoSessao;
import com.br.desafio.votacao.domain.dto.Mensagem;
import com.br.desafio.votacao.repository.AssociadoRepository;
import com.br.desafio.votacao.repository.AssociadoSessaoRepository;
import com.br.desafio.votacao.service.AssociadoSessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AssociadoSessaoServiceImpl implements AssociadoSessaoService {

    @Autowired
    private AssociadoSessaoRepository associadoRepository;

    @Override
    public ResponseEntity<?> cadastrar(AssociadoSessao associado) {
        try {
            associadoRepository.save(associado);

            return new ResponseEntity<>(new Mensagem("Associado cadastrada com sucesso!", 0L, "success", true),
                    HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Mensagem("Ocorreu um erro ao tentar cadastrar a associado!", 0L, "error", true),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<AssociadoSessao> buscarByIdPauta(Long idPauta) {
       return associadoRepository.findBySessaoPautaId(idPauta);
    }

}
