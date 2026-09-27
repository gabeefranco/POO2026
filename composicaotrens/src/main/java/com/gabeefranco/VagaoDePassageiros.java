package com.gabeefranco;

public class VagaoDePassageiros extends Vagao {
    public static final int PESO_MAXIMO_PASSAGEIRO = 80;
    protected final int assentos;

    public VagaoDePassageiros(int id, int assentos) {
        super(id);
        tipo = "Vagão de Passageiros";
        this.assentos = assentos;
    }

    public int getAssentos() {
        return assentos;
    }

    public int getPesoMaximo() {
        return assentos * PESO_MAXIMO_PASSAGEIRO;
    }

    @Override
    public double getPesoMaximoToneladas() {
        return getPesoMaximo() / 1000.0;
    }

    @Override
    public String toString() {
        return tipo + " " + id + ":\n  Assentos: " + assentos + "\n  Peso Máximo: " + getPesoMaximo() + " Kg";
    }
}
