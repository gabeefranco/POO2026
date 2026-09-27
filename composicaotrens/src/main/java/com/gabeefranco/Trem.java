package com.gabeefranco;

public class Trem {
    private final int id;
    private int qtdCarros;
    private CarroFerroviario primeiroCarro;
    private CarroFerroviario ultimoCarro;

    public Trem(int id) {
        this.id = id;
    }
    public void adicionarCarro(CarroFerroviario c) {
        if (primeiroCarro == null) {
            if (!(c instanceof Locomotiva)) {
                throw new IllegalArgumentException(
                    "O primeiro carro ferroviário de um trem deve ser uma locomotiva");
            }
        } else {
            if (!(ultimoCarro instanceof Locomotiva) && c instanceof Locomotiva) {
                throw new IllegalArgumentException(
                    "Não é possível engatar uma locomotiva depois de um vagão");
            }
            if (c instanceof Vagao v) {
                double capacidade = getCapacidadeTracao();
                double pesoAtual = getPesoTotalVagoes();
                double pesoNovo = v.getPesoMaximoToneladas();
                if (pesoAtual + pesoNovo > capacidade) {
                    throw new IllegalArgumentException(
                        "O peso máximo do vagão (" + pesoNovo + "t) excede a capacidade de tração "
                        + "disponível (restam " + (capacidade - pesoAtual) + "t de " + capacidade + "t)");
                }
            }
        }

        c.setTrem(this);
        if (primeiroCarro == null) {
            primeiroCarro = c;
        } else {
            ultimoCarro.setProximo(c);
        }
        ultimoCarro = c;
        qtdCarros++;
    }
    public CarroFerroviario removerUltimoCarro() {
        if (ultimoCarro == null) {
            return null;
        }

        CarroFerroviario removido = ultimoCarro;

        if (primeiroCarro == ultimoCarro) {
            primeiroCarro = null;
            ultimoCarro = null;
        } else {
            CarroFerroviario aux = primeiroCarro;
            while (aux.getProximo() != ultimoCarro) {
                aux = aux.getProximo();
            }
            aux.setProximo(null);
            ultimoCarro = aux;
        }

        removido.setTrem(null);
        removido.setProximo(null);
        qtdCarros--;
        return removido;
    }

    public double getCapacidadeTracao() {
        double total = 0;
        CarroFerroviario aux = primeiroCarro;
        while (aux != null) {
            if (aux instanceof Locomotiva l) {
                total += l.getPesoMaximoTracao();
            }
            aux = aux.getProximo();
        }
        return total;
    }

    public double getPesoTotalVagoes() {
        double total = 0;
        CarroFerroviario aux = primeiroCarro;
        while (aux != null) {
            if (aux instanceof Vagao v) {
                total += v.getPesoMaximoToneladas();
            }
            aux = aux.getProximo();
        }
        return total;
    }

    public int getId() {
        return id;
    }

    public int getQtdCarros() {
        return qtdCarros;
    }

    public CarroFerroviario getPrimeiroCarro() {
        return primeiroCarro;
    }

    public CarroFerroviario getUltimoCarro() {
        return ultimoCarro;
    }

    public String listaDeVagoes() {
        if (primeiroCarro == null) {
            return "  (trem vazio)\n";
        }
        StringBuilder resultado = new StringBuilder();
        CarroFerroviario aux = primeiroCarro;
        while (aux != null) {
            resultado.append(aux.toString()).append("\n");
            aux = aux.getProximo();
        }
        return resultado.toString();
    }

    @Override
    public String toString() {
        int qtdLocomotiva = 0;
        int qtdVagoes = 0;
        int qtdPassageiros = 0;
        int assentosRestaurante = 0;
        int cargaNaoRefrigerada = 0;
        int cargaRefrigerada = 0;

        CarroFerroviario aux = primeiroCarro;
        while (aux != null) {
            if (aux instanceof Locomotiva) {
                qtdLocomotiva++;
            }
            if (aux instanceof Vagao) {
                qtdVagoes++;
            }
            if (aux instanceof VagaoRestaurante v) {
                assentosRestaurante += v.getAssentos();
            } else if (aux instanceof VagaoDePassageiros v) {
                qtdPassageiros += v.getAssentos();
            }
            if (aux instanceof VagaoDeCargaRefrigerado v) {
                cargaRefrigerada += v.getCapacidadeCarga();
            } else if (aux instanceof VagaoDeCarga v) {
                cargaNaoRefrigerada += v.getCapacidadeCarga();
            }
            aux = aux.getProximo();
        }

        StringBuilder resultado = new StringBuilder();
        resultado.append("Trem ").append(id).append("\n")
            .append("  Locomotivas: ").append(qtdLocomotiva).append("\n")
            .append("  Vagões: ").append(qtdVagoes).append("\n")
            .append("  Capacidade de Passageiros: ").append(qtdPassageiros).append("\n")
            .append("  Assentos em Vagões Restaurante: ").append(assentosRestaurante).append("\n")
            .append("  Capacidade de Carga Não Refrigerada: ").append(cargaNaoRefrigerada).append(" toneladas\n")
            .append("  Capacidade de Carga Refrigerada: ").append(cargaRefrigerada).append(" toneladas\n");
        return resultado.toString();
    }
}
