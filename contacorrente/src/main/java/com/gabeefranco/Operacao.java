package com.gabeefranco;

import java.time.LocalDate;

public class Operacao {
    private LocalDate data;
    private TipoOperacao tipo;
    private double valor;

    public Operacao(LocalDate data, TipoOperacao tipo, double valor) {
        this.data = data;
        this.tipo = tipo;
        if (valor < 0) {
            throw new IllegalArgumentException("Valor de operação não pode ser negativo");
        }
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public TipoOperacao getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

}
