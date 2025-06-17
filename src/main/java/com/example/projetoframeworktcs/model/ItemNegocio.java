package com.example.projetoframeworktcs.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class ItemNegocio {
    @OneToOne
    private Produto produto;

    private Integer qtd;

    public ItemNegocio(Produto produto, Integer qtd) {
        this.produto = produto;
        this.qtd = qtd;
    }

    public Produto getProduto() {
        return produto;
    }

    public Integer getQtd() {
        return qtd;
    }
}
