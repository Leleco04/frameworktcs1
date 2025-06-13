package com.example.projetoframeworktcs.model;

import com.example.projetoframeworktcs.enumeracao.Categoria;
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
    private double valorCompra;
    private double valorVenda;
    private int qtdEstoque;


    private long iDCategoria;


    public void addEstoque(int quantidade) {
        this.qtdEstoque += quantidade;
    }

    public void removeEstoque(int quantidade) {
        this.qtdEstoque -= quantidade;
    }


    // NAO SEI SE É OBRIGATORIO  O US DO EXIBIR


    public String exibirInformacoes() {
        return String.format(
                "\nNome do produto: %s\nValor de compra: R$%.2f\nValor de venda: R$%.2f\nQuantidade em estoque: %d\nCategoria: %s\n",
                nome, valorCompra, valorVenda, qtdEstoque, iDCategoria);

    }
}