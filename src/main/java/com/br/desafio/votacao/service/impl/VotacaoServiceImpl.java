package com.br.desafio.votacao.service.impl;

import com.br.desafio.votacao.domain.Associado;
import com.br.desafio.votacao.domain.AssociadoSessao;
import com.br.desafio.votacao.domain.dto.Mensagem;
import com.br.desafio.votacao.domain.dto.VotacaoDTO;
import com.br.desafio.votacao.domain.enuns.VotoEnum;
import com.br.desafio.votacao.service.AssociadoService;
import com.br.desafio.votacao.service.AssociadoSessaoService;
import com.br.desafio.votacao.service.SessaoService;
import com.br.desafio.votacao.service.VotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.List;


@Service
public class VotacaoServiceImpl implements VotacaoService {

    @Autowired
    private SessaoService sessaoService;

    @Autowired
    private AssociadoService associadoService;

    @Autowired
    private AssociadoSessaoService associadoSessaoService;

    @Override
    public ResponseEntity<?> votar(VotacaoDTO votacaoDTO) {
        try {
            var sessao = sessaoService.getSessaoAbertaIdPauta(votacaoDTO.getIdPauta());
            if (!ObjectUtils.isEmpty(sessao)) {
                var associadoSessao = new AssociadoSessao();
                associadoSessao.setSessao(sessao);
                associadoSessao.setAssociado((Associado) associadoService.buscarPorId(votacaoDTO.getIdAssociado()).getBody());
                associadoSessao.setVoto(votacaoDTO.getVotoEnum());
                associadoSessaoService.cadastrar(associadoSessao);
                return new ResponseEntity<>(new Mensagem("Voto Realizado!", 0L, "success", true),
                        HttpStatus.OK);
            }
            return new ResponseEntity<>(new Mensagem("Sessão fechada para voto!!", 0L, "success", true),
                    HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Mensagem("Ocorreu um erro ao tentar cadastrar a pauta!", 0L, "error", true),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> resultadoVotacaoPauta(Long idPauta) {
        List<AssociadoSessao> associadoSessoes = associadoSessaoService.buscarByIdPauta(idPauta);
        if(!ObjectUtils.isEmpty(associadoSessoes)){
           return ResponseEntity.ok(associadoSessoes.stream().filter(associadoSessao -> associadoSessao.getVoto().equals(VotoEnum.SIM)).count());
        }
        return new ResponseEntity<>(new Mensagem("Pauta com um total de '0' Votos", 0L, "success", true),
                HttpStatus.OK);

    }
}
