package com.gabeefranco;

import java.util.ArrayList;
import java.util.List;

public class Garagem {
    private final List<Locomotiva> locomotivas = new ArrayList<>();
    private final List<Vagao> vagoes = new ArrayList<>();

    public void adicionar(CarroFerroviario c) {
        if (c instanceof Locomotiva l) {
            locomotivas.add(l);
        } else if (c instanceof Vagao v) {
            vagoes.add(v);
        }
    }

    public void remover(CarroFerroviario c) {
        if (c instanceof Locomotiva l) {
            locomotivas.remove(l);
        } else if (c instanceof Vagao v) {
            vagoes.remove(v);
        }
    }

    public List<Locomotiva> getLocomotivas() {
        return locomotivas;
    }

    public List<Vagao> getVagoes() {
        return vagoes;
    }

    public List<CarroFerroviario> getTodos() {
        List<CarroFerroviario> todos = new ArrayList<>();
        todos.addAll(locomotivas);
        todos.addAll(vagoes);
        return todos;
    }

    public String listar() {
        if (locomotivas.isEmpty() && vagoes.isEmpty()) {
            return "  (garagem vazia)\n";
        }
        StringBuilder sb = new StringBuilder();
        for (Locomotiva l : locomotivas) {
            sb.append(l.toString()).append("\n");
        }
        for (Vagao v : vagoes) {
            sb.append(v.toString()).append("\n");
        }
        return sb.toString();
    }
}
