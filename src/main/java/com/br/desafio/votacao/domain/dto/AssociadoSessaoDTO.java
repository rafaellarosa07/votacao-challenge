package com.br.desafio.votacao.domain.dto;

import com.br.desafio.votacao.domain.Associado;
import com.br.desafio.votacao.domain.Sessao;
import com.br.desafio.votacao.domain.enuns.VotoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoSessaoDTO {

    private Associado associado;
    private Sessao sessao;
    private VotoEnum voto;



}
