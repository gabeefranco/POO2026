package com.gabeefranco.jogo21;

public class App {
    private static Mesa mesa = new Mesa();

    public static void main(String[] args) {
        while (true) {
            mesa.iniciarRodada();
        }
    }
}
