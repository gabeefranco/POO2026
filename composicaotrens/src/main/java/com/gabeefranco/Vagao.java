package com.gabeefranco;

public abstract class Vagao extends CarroFerroviario {
    public Vagao(int id) {
        super(id);
    }
    public abstract double getPesoMaximoToneladas();
}
