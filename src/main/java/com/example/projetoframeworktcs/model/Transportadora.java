package com.example.projetoframeworktcs.model;

import com.example.projetoframeworktcs.model.enums.Local;
import jakarta.persistence.Entity;
import lombok.Getter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transportadora implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Local local;
    private String nome;
    private Integer qtdParceiras;
    private Double valorFrete;
    private Double toneladas;
    private String transportadoraEscolhida;

    public Transportadora(Local local, String nome, Double valorFrete, String transportadoraEscolhida, Double toneladas) {
        this.local = local;
        this.nome = nome;
        this.qtdParceiras = 3;
        this.valorFrete = valorFrete;
        this.transportadoraEscolhida = transportadoraEscolhida;
        this.toneladas = toneladas;
    }

}

