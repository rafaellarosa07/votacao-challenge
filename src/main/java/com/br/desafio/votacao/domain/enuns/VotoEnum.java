package com.br.desafio.votacao.domain.enuns;

import java.io.Serializable;

public enum VotoEnum implements Serializable {
    SIM("S"), NÃO("N");

    private String voto;

    VotoEnum(String s) {
        this.voto = s;
    }

    @Override
    public String toString() {
        return this.voto;
    }
}
