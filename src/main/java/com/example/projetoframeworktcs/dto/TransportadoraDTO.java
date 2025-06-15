package com.example.projetoframeworktcs.dto;

import com.example.projetoframeworktcs.model.enums.Local;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransportadoraDTO {
    private Local local;
    private String nome;
    private Double toneladas;
    private String transportadoraEscolhida;
}
