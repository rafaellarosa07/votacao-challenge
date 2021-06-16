package com.br.desafio.votacao.domain.dto;

import com.br.desafio.votacao.domain.enuns.VotoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotacaoDTO {

    private Long idAssociado;
    private Long idPauta;
    private VotoEnum votoEnum;
}
