package com.gabeefranco;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ContaCorrenteComExtrato {
    private int numero;
    private String correntista;
    private double saldo;
    private boolean ativa;
    private Operacao[] extrato;
    private int extratoIdx;

    public ContaCorrenteComExtrato(int numero, String correntista) {
        if (numero < 0) {
            throw new IllegalArgumentException("Número da conta não pode ser negativo.");
        }
        if (correntista == null || correntista.length() < 2) {
            throw new IllegalArgumentException("Correntista não pode ser nulo e deve ter pelo menos dois caracteres.");
        }
        this.numero = numero;
        this.correntista = correntista;
        this.saldo = 0.0;
        this.ativa = true;
        this.extrato = new Operacao[1024];
        this.extratoIdx = 0;
    }

    public int getNumero() {
        return numero;
    }

    public String getCorrentista() {
        return correntista;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void deposita(double valor) {
        if (!ativa) {
            throw new IllegalStateException("Operação não permitida: conta inativa.");
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor do depósito deve ser positivo.");
        }
        extrato[extratoIdx] = new Operacao(LocalDate.now(), "DEPOSITO", valor);
        extratoIdx++;
        saldo += valor;
    }

    public void saca(double valor) {
        if (!ativa) {
            throw new IllegalStateException("Operação não permitida: conta inativa.");
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor do saque deve ser positivo.");
        }
        if (valor >= saldo) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
        extrato[extratoIdx] = new Operacao(LocalDate.now(), "RETIRADA", valor);
        extratoIdx++;
        saldo -= valor;
    }

    public void transfere(double valor, ContaCorrenteComExtrato contaDestino) {
        if (!ativa) {
            throw new IllegalStateException("Operação não permitida: conta inativa.");
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor da transferência deve ser positivo.");
        }
        if (valor > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
        if (contaDestino == null) {
            throw new NullPointerException("A conta desctino não pode ser nula");
        }
        if (!contaDestino.isAtiva()) {
            throw new IllegalStateException("Conta destino inativa");
        }
        extrato[extratoIdx] = new Operacao(LocalDate.now(), "RETIRADA", valor);
        extratoIdx++;
        // Faz a transferencia
        saldo -= valor;
        contaDestino.deposita(valor);
    }

    public void fecha() {
        if (saldo > 0.0) {
            throw new IllegalArgumentException("A conta ainda tem saldo e não pode ser fechada");
        }
        ativa = false;
    }

    public Operacao[] getExtrato() {
        return extrato;
    }

    public Operacao[] extratoMesEAno(int ano, int mes) {
        Operacao[] extratoFiltrado = new Operacao[extrato.length];
        int extratoFiltradoIdx = 0;
        for (int i = 0; i < extrato.length; i++) {
            Operacao op = extrato[i];
            if (op != null && op.getData().getYear() == ano && op.getData().getMonthValue() == mes) {
                extratoFiltrado[extratoFiltradoIdx] = op;
                extratoFiltradoIdx++;
            }
        }

        return extratoFiltrado;

    }

    public String extratoMesEAnoFormatado(int ano, int mes) {
        String resultado = "EXTRATO: MÊS " + mes + " DO ANO " + ano + ":\n";
        for (int i = 0; i < extrato.length; i++) {
            Operacao op = extrato[i];
            if (op != null && op.getData().getYear() == ano && op.getData().getMonthValue() == mes) {
                String dataFormatada = op.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                resultado += "OPERAÇÃO EM " + dataFormatada + ": TIPO=" + op.getTipo() + ", VALOR=" + op.getValor()
                        + "\n";

            }
        }

        return resultado;
    }

    @Override
    public String toString() {
        return "ContaCorrente{" +
                "numero=" + numero +
                ", correntista='" + correntista + '\'' +
                ", saldo=" + saldo +
                ", ativa=" + ativa +
                '}';
    }
}
