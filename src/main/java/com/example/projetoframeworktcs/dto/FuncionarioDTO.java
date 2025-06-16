package com.example.projetoframeworktcs.dto;

import com.example.projetoframeworktcs.model.Setor;
import com.example.projetoframeworktcs.model.enums.Genero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioDTO {
    private String nome;
    private String sobrenome;
    private Integer idade;
    private Genero genero;
    private Setor setor;
    private Double salarioBruto;
}
