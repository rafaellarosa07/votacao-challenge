package com.br.desafio.votacao.service.impl;

import com.br.desafio.votacao.domain.Associado;
import com.br.desafio.votacao.domain.AssociadoSessao;
import com.br.desafio.votacao.domain.Sessao;
import com.br.desafio.votacao.domain.dto.Mensagem;
import com.br.desafio.votacao.domain.dto.ValidacaoCpfDTO;
import com.br.desafio.votacao.domain.dto.VotacaoDTO;
import com.br.desafio.votacao.domain.enuns.StatusCPFEnum;
import com.br.desafio.votacao.domain.enuns.VotoEnum;
import com.br.desafio.votacao.service.AssociadoService;
import com.br.desafio.votacao.service.AssociadoSessaoService;
import com.br.desafio.votacao.service.SessaoService;
import com.br.desafio.votacao.service.VotacaoService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

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
            var associado = (Associado) associadoService.buscarPorId(votacaoDTO.getIdAssociado()).getBody();
            ValidacaoCpfDTO validacao = this.validateCPF(associado.getCpf());
            if(validacao.getStatus().equals(StatusCPFEnum.VALIDO)) {
                var sessao = sessaoService.getSessaoAbertaIdPauta(votacaoDTO.getIdPauta());
                if (!ObjectUtils.isEmpty(sessao)) {
                        preencherAssociadoSessao(sessao, votacaoDTO);
                    return new ResponseEntity<>(new Mensagem("Voto Realizado!", 0L, "success", true),
                            HttpStatus.OK);
                }
                return new ResponseEntity<>(new Mensagem("Sessão fechada para voto!!", 0L, "success", true),
                        HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(new Mensagem("Associado não apto para votar!!", 0L, "success", true),
                    HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Mensagem("Ocorreu um erro ao tentar cadastrar a pauta!", 0L, "error", true),
                    HttpStatus.BAD_REQUEST);
        }
    }

    private void preencherAssociadoSessao(Sessao sessao, VotacaoDTO votacaoDTO) throws Exception {
        var associadoSessao = new AssociadoSessao();
        associadoSessao.setSessao(sessao);
        associadoSessao.setAssociado((Associado) associadoService.buscarPorId(votacaoDTO.getIdAssociado()).getBody());
        associadoSessao.setVoto(votacaoDTO.getVotoEnum());
        associadoSessaoService.cadastrar(associadoSessao);
    }

    private static ValidacaoCpfDTO validateCPF(String cpf)
    {
        final String uri = "https://user-info.herokuapp.com/users/" + cpf;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result);
        ValidacaoCpfDTO validacaoCpfDTO = new Gson().fromJson(result, ValidacaoCpfDTO.class);

        return validacaoCpfDTO;
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
