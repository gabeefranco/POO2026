package com.gabeefranco;

public class VagaoRestaurante extends VagaoDePassageiros {
    public VagaoRestaurante(int id, int mesas) {
        super(id, mesas * 4);
        tipo = "Vagão Restaurante";
    }

    @Override
    public int getPesoMaximo() {
        // Peso do cozinheiro + garçom (equivalente a mais 2 "passageiros")
        // e mais 1000 Kg referentes ao peso da cozinha.
        return (assentos + 2) * PESO_MAXIMO_PASSAGEIRO + 1000;
    }

    @Override
    public String toString() {
        return tipo + " " + id + ":\n  Assentos: " + assentos + "\n  Peso Máximo: " + getPesoMaximo() + " Kg";
    }
}
