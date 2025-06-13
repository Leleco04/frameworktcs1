package com.example.projetoframeworktcs.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class ItemNegocio {
    private Produto produto;
    private int qtd;

    public ItemNegocio(Produto produto, int qtd) {
        this.produto = produto;
        this.qtd = qtd;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQtd() {
        return qtd;
    }
}
