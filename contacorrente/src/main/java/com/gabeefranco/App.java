package com.gabeefranco;

import java.time.LocalDate;

public class App {

    public static void main(String[] args) {
        ContaCorrenteComExtrato conta = new ContaCorrenteComExtrato(99, "Gabriel Franco");
        System.out.println(conta.toString());
        conta.deposita(20.99);
        System.out.println("DEPOSITADOS R$ 20.99");
        System.out.println(conta.toString());
        conta.saca(10.50);
        System.out.println("SACADOS R$ 10.50");
        System.out.println(conta.toString());

        // mostrar extrato do mês e ano atual
        LocalDate hoje = LocalDate.now();
        String extrato = conta.extratoMesEAnoFormatado(hoje.getYear(), hoje.getMonthValue());
        System.out.println("MOSTRANDO EXTRATO FORMATADO");
        System.out.println(extrato);

    }
}
