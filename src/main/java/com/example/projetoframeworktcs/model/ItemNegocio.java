package com.example.projetoframeworktcs.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class ItemNegocio {
    @OneToOne
    private Produto produto;

    private int qtd;

    public ItemNegocio(Produto produto, int qtd) {
        this.produto = produto;
        this.qtd = qtd;
    }
}
