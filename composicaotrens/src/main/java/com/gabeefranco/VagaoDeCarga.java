package com.gabeefranco;

public class VagaoDeCarga extends Vagao {
    private final int capacidadeCarga;

    public VagaoDeCarga(int id, int capacidadeCarga) {
        super(id);
        tipo = "Vagão de Carga";
        this.capacidadeCarga = capacidadeCarga;
    }

    public int getCapacidadeCarga() {
        return capacidadeCarga;
    }

    @Override
    public double getPesoMaximoToneladas() {
        return capacidadeCarga;
    }

    @Override
    public String toString() {
        return tipo + " " + id + ":\n  Capacidade de Carga: " + capacidadeCarga + " toneladas";
    }
}
