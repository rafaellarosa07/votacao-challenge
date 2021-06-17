package com.br.desafio.votacao.domain.enuns;

import java.io.Serializable;

public enum StatusCPFEnum implements Serializable {
    INVALIDO("UNABLE_TO_VOTE"), VALIDO("ABLE_TO_VOTE");

    private String isValido;

    StatusCPFEnum(String s) {
        this.isValido = s;
    }

    @Override
    public String toString() {
        return this.isValido;
    }
}
