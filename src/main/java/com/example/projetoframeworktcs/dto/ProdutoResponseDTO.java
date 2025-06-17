package com.example.projetoframeworktcs.dto;

public record ProdutoResponseDTO (
        long id,
        String nome,
        String descricao,
        double valorCompra,
        double valorVenda,
        int qtdEstoque,
        String nomeCategoria
) {}