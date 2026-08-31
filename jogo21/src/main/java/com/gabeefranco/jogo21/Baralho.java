package com.gabeefranco.jogo21;

import java.util.*;

public class Baralho {
    private final List<Carta> cartas = new ArrayList<>();

    public Baralho() {
        for (Naipe n : Naipe.values()) {
            for (Valor v : Valor.values()) {
                cartas.add(new Carta(n, v));
            }
        }
        Collections.shuffle(cartas);
    }

    public Carta puxarCarta() {
        return cartas.remove(0);
    }
}
