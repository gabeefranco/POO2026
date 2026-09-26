package com.gabeefranco;

public class VagaoRestaurante extends VagaoDePassageiros {
    public VagaoRestaurante(int id, int mesas) {
        super(id, mesas * 4);
        tipo = "Vagão Restaurante";
    }

    @Override
    public int getPesoMaximo() {
        return (assentos + 2) * PESO_MAXIMO_PASSAGEIRO + 1000;
    }

}
