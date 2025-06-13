package com.example.projetoframeworktcs.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class Salario {

    private Double vale;
    private Double planoSaude;
    private Double planoOdontologico;
    private Double bonusParticipacao;
    private Double taxaAliquota;
    private Double salarioBruto;
    private Double salarioLiquido;

    public Salario(Double vale, Double planoSaude, Double planoOdontologico, Double bonusParticipacao, Double salarioBruto, Double salarioLiquido) {
        this.vale = vale;
        this.planoSaude = planoSaude;
        this.planoOdontologico = planoOdontologico;
        this.bonusParticipacao = bonusParticipacao;
        this.taxaAliquota = calcularTaxaAliquota();
        this.salarioBruto = salarioBruto;
        this.salarioLiquido = salarioLiquido;
    }

    private Double calcularTaxaAliquota() {
        if (salarioBruto <= 2428.80) {
            return 0.0;
        } else if (salarioBruto <= 2826.65) {
            return 0.075;
        } else if (salarioBruto <= 3751.05) {
            return 0.15;
        } else if (salarioBruto <= 4664.68) {
            return 0.225;
        } else {
            return 0.275;
        }
    }
}
