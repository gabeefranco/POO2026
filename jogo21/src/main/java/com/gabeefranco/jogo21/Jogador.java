package com.gabeefranco.jogo21;

import java.util.*;

public class Jogador {
    private final String nome;
    private final List<Carta> mao = new ArrayList<>();
    private int dinheiro;

    public Jogador(String nome, int dinheiroInicial) {
        this.nome = nome;
        this.dinheiro = dinheiroInicial;
    }

    public void receberCarta(Carta c) {
        mao.add(c);
    }

    public void limparMao() {
        mao.clear();
    }

    public int calcularPontuacao() {
        int total = 0;
        int ases = 0;
        for (Carta c : mao) {
            total += c.getPontos();
            if (c.getPontos() == 11)
                ases++;
        }
        while (total > 21 && ases > 0) {
            total -= 10;
            ases--;
        }
        return total;
    }

    public int getDinheiro() {
        return dinheiro;
    }

    public void ganharDinheiro(int valor) {
        dinheiro += valor;
    }

    public void perderDinheiro(int valor) {
        dinheiro -= valor;
    }

    @Override
    public String toString() {
        return nome + " -> " + mao + " (Pontuação: " + calcularPontuacao() + ", Dinheiro: $" + dinheiro + ")";
    }
}
