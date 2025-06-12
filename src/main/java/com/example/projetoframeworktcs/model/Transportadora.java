package com.example.projetoframeworktcs.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
public class Transportadora implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer idLocal;
    private Integer qtdParceiras;
    private Double valorFrete;
    private Double toneladas;
    private String transportadoraEscolhida;

    public Transportadora(Integer idLocal ,String transportadoraEscolhida, Double toneladas, Double valorFrete) {
        this.idLocal = 0;
        this.qtdParceiras = 3;
        this.transportadoraEscolhida = transportadoraEscolhida;
        this.toneladas = toneladas;
        this.valorFrete = valorFrete;
    }
}

