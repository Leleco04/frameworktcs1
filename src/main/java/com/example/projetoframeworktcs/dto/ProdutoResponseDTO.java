package com.example.projetoframeworktcs.dto;

public record ProdutoResponseDTO (
        Long id,
        String nome,
        String descricao,
        Double valorCompra,
        Double valorVenda,
        Integer qtdEstoque,
        String nomeCategoria
) {}