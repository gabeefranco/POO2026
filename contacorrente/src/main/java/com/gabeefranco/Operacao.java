package com.gabeefranco;

import java.time.LocalDate;

public class Operacao {
    private LocalDate data;
    private String tipo;
    private double valor;

    public Operacao(LocalDate data, String tipo, double valor) {
        this.data = data;
        this.tipo = tipo;
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

}
