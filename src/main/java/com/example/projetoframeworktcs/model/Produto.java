package com.example.projetoframeworktcs.model;

import com.example.projetoframeworktcs.model.enums.Categoria;
import jakarta.persistence.*;
        import lombok.*;

@Entity
@Data // @DATA PUXA AO MSM TEMPO O  @Getter, @Setter, @ToString, @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private Double valorCompra;
    private Double valorVenda;
    private Integer qtdEstoque;
    //private Categoria categoria;


    public Produto(String nome, String descricao, Double valorCompra, Double valorVenda, Integer qtdEstoque) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.qtdEstoque = qtdEstoque;
    }

    public void addEstoque(Integer quantidade) {
        this.qtdEstoque += quantidade;
    }

    public void removeEstoque(Integer quantidade) {
        this.qtdEstoque -= quantidade;
    }

    /* public String exibirInformacoes() {
        return String.format(
                "\nNome do produto: %s\nValor de compra: R$%.2f\nValor de venda: R$%.2f\nQuantidade em estoque: %d\nCategoria: %s\n",
                nome, valorCompra, valorVenda, qtdEstoque, categoria.getCategoria());
    } */
}