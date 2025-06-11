package com.example.projetoframeworktcs.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarFuncionarioDTO {
    private String nome;
    private String sobrenome;
    private Integer idade;
    private Integer id_genero;
    private Integer id_setor;
}
