package com.gabeefranco;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Trem> trens = new ArrayList<>();
    private static final Garagem garagem = new Garagem();

    public static void main(String[] args) {
        popularGaragem();

        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerInteiro("Escolha uma opção: ");
            switch (opcao) {
                case 1 -> criarTrem();
                case 2 -> editarTrem();
                case 3 -> listarTrens();
                case 4 -> listarCaracteristicasTrem();
                case 5 -> desfazerTrem();
                case 6 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 6);

        scanner.close();
    }

    /** Insere um conjunto inicial de locomotivas e vagões livres na garagem. */
    private static void popularGaragem() {
        garagem.adicionar(new Locomotiva(1, 500));
        garagem.adicionar(new Locomotiva(2, 300));
        garagem.adicionar(new Locomotiva(3, 700));

        garagem.adicionar(new VagaoDeCarga(101, 50));
        garagem.adicionar(new VagaoDeCarga(102, 60));
        garagem.adicionar(new VagaoDeCargaRefrigerado(103, 40));
        garagem.adicionar(new VagaoDeCargaRefrigerado(104, 30));

        garagem.adicionar(new VagaoDePassageiros(201, 40));
        garagem.adicionar(new VagaoDePassageiros(202, 60));
        garagem.adicionar(new VagaoRestaurante(301, 10));
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n===== Sistema de Composição de Trens =====");
        System.out.println("1 - Criar um trem");
        System.out.println("2 - Editar um trem");
        System.out.println("3 - Listar trens no pátio");
        System.out.println("4 - Listar características de um trem específico");
        System.out.println("5 - Desfazer um trem");
        System.out.println("6 - Fim");
    }

    private static void criarTrem() {
        int id = lerInteiro("Informe o identificador do novo trem: ");
        if (buscarTrem(id) != null) {
            System.out.println("Já existe um trem com esse identificador!");
            return;
        }
        trens.add(new Trem(id));
        System.out.println("Trem " + id + " criado com sucesso!");
    }

    private static void editarTrem() {
        int id = lerInteiro("Informe o identificador do trem a editar: ");
        Trem trem = buscarTrem(id);
        if (trem == null) {
            System.out.println("Trem não encontrado!");
            return;
        }

        int opcao;
        do {
            System.out.println("\n--- Editando Trem " + id + " ---");
            System.out.println("1 - Engatar um carro ferroviário");
            System.out.println("2 - Remover o último carro ferroviário");
            System.out.println("3 - Listar carros ferroviários na garagem");
            System.out.println("4 - Listar carros ferroviários do trem");
            System.out.println("5 - Encerrar edição");
            opcao = lerInteiro("Escolha uma opção: ");
            switch (opcao) {
                case 1 -> engatarCarro(trem);
                case 2 -> removerCarro(trem);
                case 3 -> System.out.print("\nCarros na garagem:\n" + garagem.listar());
                case 4 -> System.out.print("\nCarros do trem " + id + ":\n" + trem.listaDeVagoes());
                case 5 -> System.out.println("Encerrando edição do trem " + id + ".");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    private static void engatarCarro(Trem trem) {
        List<CarroFerroviario> disponiveis = garagem.getTodos();
        if (disponiveis.isEmpty()) {
            System.out.println("Não há carros ferroviários livres na garagem!");
            return;
        }

        System.out.println("\nCarros disponíveis na garagem:");
        for (int i = 0; i < disponiveis.size(); i++) {
            String resumo = disponiveis.get(i).toString().replace("\n", " | ");
            System.out.println((i + 1) + " - " + resumo);
        }

        int escolha = lerInteiro("Escolha o carro a engatar (0 para cancelar): ");
        if (escolha == 0) {
            return;
        }
        if (escolha < 1 || escolha > disponiveis.size()) {
            System.out.println("Opção inválida!");
            return;
        }

        CarroFerroviario c = disponiveis.get(escolha - 1);
        try {
            trem.adicionarCarro(c);
            garagem.remover(c);
            System.out.println("Carro engatado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Não foi possível engatar: " + e.getMessage());
        }
    }

    private static void removerCarro(Trem trem) {
        CarroFerroviario removido = trem.removerUltimoCarro();
        if (removido == null) {
            System.out.println("O trem está vazio!");
            return;
        }
        garagem.adicionar(removido);
        System.out.println("Carro removido e devolvido à garagem: "
            + removido.toString().replace("\n", " "));
    }

    private static void listarTrens() {
        if (trens.isEmpty()) {
            System.out.println("Não há trens no pátio.");
            return;
        }
        System.out.println("\nTrens no pátio:");
        for (Trem t : trens) {
            System.out.println("  Trem " + t.getId() + " (" + t.getQtdCarros() + " carro(s))");
        }
    }

    private static void listarCaracteristicasTrem() {
        int id = lerInteiro("Informe o identificador do trem: ");
        Trem trem = buscarTrem(id);
        if (trem == null) {
            System.out.println("Trem não encontrado!");
            return;
        }
        System.out.println("\n" + trem);
    }

    private static void desfazerTrem() {
        int id = lerInteiro("Informe o identificador do trem a desfazer: ");
        Trem trem = buscarTrem(id);
        if (trem == null) {
            System.out.println("Trem não encontrado!");
            return;
        }

        CarroFerroviario removido;
        while ((removido = trem.removerUltimoCarro()) != null) {
            garagem.adicionar(removido);
        }
        trens.remove(trem);
        System.out.println("Trem " + id + " desfeito. Seus carros voltaram para a garagem.");
    }

    private static Trem buscarTrem(int id) {
        for (Trem t : trens) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    private static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.print("Valor inválido. " + mensagem);
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }
}
