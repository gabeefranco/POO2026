package com.gabeefranco;

public class VagaoDeCargaRefrigerado extends VagaoDeCarga {
    public VagaoDeCargaRefrigerado(int id, int capacidadeCarga) {
        super(id, (int) (capacidadeCarga * 1.15));
        tipo = "Vagão de Carga Refrigerado";
    }

}
