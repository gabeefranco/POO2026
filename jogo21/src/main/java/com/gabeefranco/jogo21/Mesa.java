package com.gabeefranco.jogo21;

import java.util.Scanner;

public class Mesa {
    private final Baralho baralho;
    private final Jogador jogador;
    private final Jogador dealer;
    private final Scanner sc;

    public Mesa() {
        this.baralho = new Baralho();
        this.jogador = new Jogador("Jogador", 100);
        this.dealer = new Jogador("Dealer", 0);
        this.sc = new Scanner(System.in);
    }

    public void iniciarRodada() {

        System.out.println("Você tem $" + jogador.getDinheiro());
        System.out.print("Digite o valor da aposta: ");
        int aposta = sc.nextInt();
        sc.nextLine();

        if (aposta > jogador.getDinheiro()) {
            System.out.println("Você não tem dinheiro suficiente!");
            return;
        }

        jogador.limparMao();
        dealer.limparMao();

        // Distribuição inicial
        jogador.receberCarta(baralho.puxarCarta());
        jogador.receberCarta(baralho.puxarCarta());
        dealer.receberCarta(baralho.puxarCarta());
        dealer.receberCarta(baralho.puxarCarta());

        System.out.println(jogador);
        System.out.println("Carta visível do dealer: " + dealer.toString().split(",")[0] + "]");

        // Turno do jogador
        while (true) {
            System.out.print("Deseja 'h' para hit ou 's' para stand? ");
            String acao = sc.nextLine();
            if (acao.equalsIgnoreCase("h")) {
                jogador.receberCarta(baralho.puxarCarta());
                System.out.println(jogador);
                if (jogador.calcularPontuacao() > 21) {
                    System.out.println("Você estourou! Dealer vence.");
                    jogador.perderDinheiro(aposta);
                    return;
                }
            } else {
                break;
            }
        }

        // Turno do dealer
        while (dealer.calcularPontuacao() < 17) {
            dealer.receberCarta(baralho.puxarCarta());
        }

        System.out.println(dealer);

        // Resultado
        int pontosJogador = jogador.calcularPontuacao();
        int pontosDealer = dealer.calcularPontuacao();

        if (pontosDealer > 21 || pontosJogador > pontosDealer) {
            System.out.println("Você venceu!");
            jogador.ganharDinheiro(aposta);
        } else if (pontosJogador == pontosDealer) {
            System.out.println("Empate! A aposta é devolvida.");
        } else {
            System.out.println("Dealer venceu!");
            jogador.perderDinheiro(aposta);
        }
    }
}
