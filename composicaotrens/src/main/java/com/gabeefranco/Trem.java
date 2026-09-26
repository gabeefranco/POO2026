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
        if(primeiroCarro == null) {
            if(!(c instanceof Locomotiva)) {
                throw new IllegalArgumentException("O primeiro carro ferroviário de um trem deve ser uma locomotiva");
            }
            c.setTrem(this);
            primeiroCarro = c;
            ultimoCarro = c;
            qtdCarros++;
            return;
        }

        if(!(ultimoCarro instanceof Locomotiva) && c instanceof Locomotiva) {
            throw new IllegalArgumentException("Não é possível engatar uma locomotiva em um vagão comum");
        }

        c.setTrem(this);
        ultimoCarro.setProximo(c);
        ultimoCarro = c;
        qtdCarros++;

    }

    public void removerUltimoCarro() {
        if(primeiroCarro == ultimoCarro) {
            primeiroCarro = null;
            ultimoCarro = null;
            return;
        }
        CarroFerroviario aux = primeiroCarro;
        while(aux.getProximo() != ultimoCarro) {
            aux = aux.getProximo();
        }

        ultimoCarro.setTrem(null);
        ultimoCarro = aux;
        ultimoCarro.setProximo(null);
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
        CarroFerroviario aux = primeiroCarro;
        String resultado = "";
        do {
            resultado += aux.toString() + "\n";
            aux = aux.getProximo();
        }while(aux.getProximo() != null);

        return resultado;
    }


	public String toString() {
        int qtdLocomotiva = 0;
        int qtdVagoes = 0;
        int qtdPassageiros = 0;
        int assentosRestaurante = 0;
        int cargaNaoRefrigerada = 0;
        int cargaRefrigerada = 0;
        if(primeiroCarro != null) {
            CarroFerroviario aux = primeiroCarro;
            while(aux != null) {
                if(aux instanceof Locomotiva) {
                    qtdLocomotiva++;
                }
                if(aux instanceof Vagao) {
                    qtdVagoes++;
                }
                if(aux instanceof VagaoDePassageiros v) {
                    qtdPassageiros += v.getAssentos();
                }
                if(aux instanceof VagaoRestaurante v) {
                    assentosRestaurante += v.getAssentos();
                }
                if(aux instanceof VagaoDeCargaRefrigerado v) {
                    cargaRefrigerada += v.getCapacidadeCarga();
                } else if(aux instanceof VagaoDeCarga v) {
                    cargaNaoRefrigerada += v.getCapacidadeCarga();
                }

                aux = aux.getProximo();
            }
        }
        StringBuilder resultado = new StringBuilder();
        resultado.append("Trem " + id + "\n")
        .append("  Locomotivas: " + qtdLocomotiva + "\n")
        .append("  Vagões: " + qtdVagoes + "\n")
        .append("  Capacidade de Passageiros: " + qtdPassageiros + "\n")
        .append("  Assentos em Vagões Restaurante: " + assentosRestaurante + "\n")
        .append("  Capacidade de Carga Não Refrigerada: " + cargaNaoRefrigerada + "\n")
        .append("  Capacidade de Carga Refrigerada: " + cargaRefrigerada + "\n");
        return resultado.toString();

	}



}
