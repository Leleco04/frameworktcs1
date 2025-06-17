package com.example.projetoframeworktcs.dto;

public record CriarProdutoDTO (
        String nome,
        String descricao,
        Double valorCompra,
        Double valorVenda,
        Integer qtdEstoque,
        Long id_categoria
) {}
