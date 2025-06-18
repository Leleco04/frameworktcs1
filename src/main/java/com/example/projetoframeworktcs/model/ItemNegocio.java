package com.example.projetoframeworktcs.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToOne;

@Embeddable
public class ItemNegocio {
    @OneToOne
    private Produto produto;

    private Integer qtd;

    public ItemNegocio() {
    }

    public ItemNegocio(Produto produto, Integer qtd) {
        this.produto = produto;
        this.qtd = qtd;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }
}
