package com.gabeefranco;

public class CarroFerroviario {
    protected String tipo;
    protected int id;
    protected Trem trem;
    protected CarroFerroviario proximo;

	public CarroFerroviario(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}
	public int getId() {
		return id;
	}
	public Trem getTrem() {
		return trem;
	}
	public CarroFerroviario getProximo() {
		return proximo;
	}

	public void setTrem(Trem trem) {
		this.trem = trem;
	}
	public void setProximo(CarroFerroviario proximo) {
		this.proximo = proximo;
	}


}
