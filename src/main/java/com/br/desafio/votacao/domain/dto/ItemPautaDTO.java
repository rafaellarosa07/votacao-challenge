package com.br.desafio.votacao.domain.dto;

import com.br.desafio.votacao.domain.ItemPauta;
import com.br.desafio.votacao.domain.Pauta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemPautaDTO {

    private Long id;
    private String nome;


    public ItemPauta paraEntidade() {

        return ItemPauta.builder()
                .id(this.id)
                .nome(this.nome)
                .build();
    }

}
