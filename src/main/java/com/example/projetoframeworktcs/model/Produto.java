package com.example.projetoframeworktcs.model;

import com.example.projetoframeworktcs.model.Categoria;
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

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    public Produto(String nome, String descricao, Double valorCompra, Double valorVenda, Integer qtdEstoque, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.qtdEstoque = qtdEstoque;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public Categoria getCategoria() {
        return categoria;
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