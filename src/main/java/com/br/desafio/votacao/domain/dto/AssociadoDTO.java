package com.br.desafio.votacao.domain.dto;

import com.br.desafio.votacao.domain.Associado;
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
public class AssociadoDTO {

    private Long id;
    private String nome;
    private String cpf;

    public Associado paraEntidade() {

        return Associado.builder()
                .id(this.id)
                .nome(this.nome)
                .cpf(this.cpf)
                .build();
    }

}
