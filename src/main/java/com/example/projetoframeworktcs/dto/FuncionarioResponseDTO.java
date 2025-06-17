package com.example.projetoframeworktcs.dto;

public record FuncionarioResponseDTO (
        long id,
        String nome,
        String sobrenome,
        String genero,
        Integer idade,
        String nomeSetor
){}
