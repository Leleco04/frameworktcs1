package com.example.projetoframeworktcs.dto;

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
    private Integer id_setor;
    private Double vale;
    private Double planoSaude;
    private Double planoOdontologico;
    private Double bonusParticipacao;
    private Double taxaAliquota;
    private Double salarioBruto;
}
