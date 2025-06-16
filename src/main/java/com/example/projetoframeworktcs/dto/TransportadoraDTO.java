package com.example.projetoframeworktcs.dto;

import com.example.projetoframeworktcs.model.enums.Transportadora;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransportadoraDTO {
    private Transportadora local;
    private String nome;
    private Double toneladas;
    private String transportadoraEscolhida;
}
