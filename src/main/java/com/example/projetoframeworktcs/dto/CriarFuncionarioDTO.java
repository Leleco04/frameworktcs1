package com.example.projetoframeworktcs.dto;

public record CriarFuncionarioDTO (
        String nome,
        String sobrenome,
        String genero,
        Integer idade,
        long idSetor,
        Double salarioBruto
) {}