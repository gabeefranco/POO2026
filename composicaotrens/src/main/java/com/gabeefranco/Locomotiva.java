package com.gabeefranco;

public class Locomotiva extends CarroFerroviario {
    private final int pesoMaximoTracao;

    public Locomotiva(int id, int pesoMaximoTracao) {
        super(id);
        tipo = "Locomotiva";
        this.pesoMaximoTracao = pesoMaximoTracao;
    }

    public int getPesoMaximoTracao() {
        return pesoMaximoTracao;
    }

    @Override
    public String toString() {
        return tipo + " " + id + ":\n  Capacidade de Tração: " + pesoMaximoTracao + " toneladas";
    }
}
