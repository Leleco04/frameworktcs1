package com.example.projetoframeworktcs.dto;

public record AtualizarFuncionarioDTO (
     String nome,
     String sobrenome,
     Integer idade,
     String genero,
     Long idSetor,
     Double salarioBruto
) {}
