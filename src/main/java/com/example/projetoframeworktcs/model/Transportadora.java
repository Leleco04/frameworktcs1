package com.example.projetoframeworktcs.model;

import com.example.projetoframeworktcs.model.enums.Local;
import static com.example.projetoframeworktcs.service.TransportadoraService.buscarcidadeTransportadora;
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
    private Integer idLocal;
    private String nome;
    private Integer qtdParceiras;
    private Double valorFrete;
    private Double toneladas;
    private String transportadoraEscolhida;

    public Transportadora(Integer idLocal, String nome ,String transportadoraEscolhida, Double toneladas) {
        this.idLocal = 0;
        this.nome = nome;
        this.qtdParceiras = 3;
        this.transportadoraEscolhida = transportadoraEscolhida;
        this.toneladas = toneladas;
    }

    public Double calcularFrete(String transportadoraEscolhida, double toneladas) {
        Local local = buscarcidadeTransportadora(transportadoraEscolhida);

        if (local != null) {
            double valorFinal;
            double valorExtra = 200;
            valorFinal = valorExtra * toneladas;
            return valorFinal + local.getValorFreteFixo();
        }
        return null;
    }


}

