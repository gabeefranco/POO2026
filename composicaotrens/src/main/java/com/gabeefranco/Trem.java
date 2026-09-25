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

	@Override
	public String toString() {
        int qtdLocomotiva = 0;
        int qtdVagoes = 0;
        if(primeiroCarro != null) {
            CarroFerroviario aux = primeiroCarro;
            while(aux != null) {
                if(aux instanceof Locomotiva) {
                    qtdLocomotiva++;
                }
                if(aux instanceof Vagao) {
                    qtdVagoes++;
                }
                aux = aux.getProximo();
            }
        }
		return "Trem " + id + "\n  Locomotivas: " + qtdLocomotiva + "\n  Vagoes: " + qtdVagoes;
	}



}
