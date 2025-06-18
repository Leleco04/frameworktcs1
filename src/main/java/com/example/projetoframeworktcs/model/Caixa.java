package com.example.projetoframeworktcs.model;

import org.springframework.stereotype.Component;

@Component
public class Caixa {
   private static Double valor;

    public Caixa() {
        valor = 200000.0;
    }

    public static Double getValor() { return valor; }
    public void setValor(Double valor) { valor = valor; }

    public static void aumentarValor(double valor) {
        Caixa.valor += valor;
    }

    public static void diminuirValor(double valor) {
        Caixa.valor -= valor;
    }
}
