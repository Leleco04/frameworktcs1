package com.example.projetoframeworktcs.dto;

public record CriarFuncionarioDTO (
        String nome,
        String sobrenome,
        String genero,
        int idade,
        long idSetor
) {}
