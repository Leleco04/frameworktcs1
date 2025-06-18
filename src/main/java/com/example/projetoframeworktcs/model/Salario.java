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

    @Override
    public String toString() {
        return "**Informações salariais**" + "\n" +
                "--------------------------------------------" + "\n" +
                "Vale Refeição/Alimentação: R$" + String.format("%.2f", vale) + "\n" +
                "Cobertura do plano de saúde: R$" + String.format("%.2f/Mês", planoSaude) + "\n" +
                "Cobertura do plano Odontológico: R$" + String.format("%.2f/Mês", planoOdontologico) + "\n" +
                "Bonus de participação: R$" + String.format("%.2f", bonusParticipacao) + "\n" +
                "Aliquota aplicada: " + String.format("%.2f", (taxaAliquota * 100)) + "%" + "\n" +
                "Salario Bruto: R$" + String.format("%.2f", salarioBruto) + "\n" +
                "--------------------------------------------" + "\n";
    }

    public void setVale(Double vale) {
        this.vale = vale;
    }

    public void setPlanoSaude(Double planoSaude) {
        this.planoSaude = planoSaude;
    }

    public void setPlanoOdontologico(Double planoOdontologico) {
        this.planoOdontologico = planoOdontologico;
    }

    public void setBonusParticipacao(Double bonusParticipacao) {
        this.bonusParticipacao = bonusParticipacao;
    }

    public void setTaxaAliquota(Double taxaAliquota) {
        this.taxaAliquota = taxaAliquota;
    }

    public void setSalarioBruto(Double salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public void setSalarioLiquido(Double salarioLiquido) {
        this.salarioLiquido = salarioLiquido;
    }
}
