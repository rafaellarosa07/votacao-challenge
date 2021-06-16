package com.br.desafio.votacao.domain;

import com.br.desafio.votacao.domain.enuns.VotoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ASSOCIADO_SESSAO")
public class AssociadoSessao implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "ASSOCIADO_ID", referencedColumnName = "ID")
    private Associado associado;

    @Id
    @ManyToOne
    @JoinColumn(name = "SESSAO_ID", referencedColumnName = "ID")
    private Sessao sessao;

    @Column(name = "VOTO")
    private VotoEnum voto;



}
