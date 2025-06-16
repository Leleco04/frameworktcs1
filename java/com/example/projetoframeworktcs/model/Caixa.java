package com.example.projetoframeworktcs.model;

import org.springframework.stereotype.Component;

@Component
public class Caixa {
    private Double valor;

    public Caixa() {
        this.valor = 200000.0;
    }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
}
