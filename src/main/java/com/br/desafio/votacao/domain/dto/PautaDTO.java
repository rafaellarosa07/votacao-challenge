package com.br.desafio.votacao.domain.dto;

import com.br.desafio.votacao.domain.ItemPauta;
import com.br.desafio.votacao.domain.Pauta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PautaDTO {

    private Long id;

    private String nome;

    private LocalDateTime dataCriacao;

    private List<ItemPautaDTO> itensPauta;


    public Pauta paraEntidade() {

        return Pauta.builder()
                .id(this.id)
                .nome(this.nome)
                .dataCriacao(this.dataCriacao)
                .build();
    }
}
