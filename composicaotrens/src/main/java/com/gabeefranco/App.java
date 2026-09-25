package com.gabeefranco;

public class App {
    public static void main(String[] args) {
        Trem trem1 = new Trem(1);
        trem1.adicionarCarro(new Locomotiva(1));
        trem1.adicionarCarro(new VagaoDeCarga(2));
        System.out.println(trem1);

        // Trem trem2 = new Trem(2);
        // trem2.adicionarCarro(new VagaoDeCarga(3));

        Trem trem2 = new Trem(2);
        trem2.adicionarCarro(new Locomotiva(3));
        trem2.adicionarCarro(new VagaoDeCarga(4));
        trem2.adicionarCarro(new Locomotiva(5));
    }
}
