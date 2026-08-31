package com.gabeefranco.jogo21;

public class Carta {
    private final Naipe naipe;
    private final Valor valor;

    public Carta(Naipe naipe, Valor valor) {
        this.naipe = naipe;
        this.valor = valor;
    }

    public int getPontos() {
        return valor.getPontos();
    }

    public String toString() {
        return valor + " de " + naipe;
    }

}
