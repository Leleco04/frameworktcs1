package com.example.projetoframeworktcs.dto;

public record CriarProdutoDTO (
        String nome,
        String descricao,
        double valorCompra,
        double valorVenda,
        int qtdEstoque,
        long idCategoria) {
}