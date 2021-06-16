package com.br.desafio.votacao.domain.enuns;

public enum VotoEnum {
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
