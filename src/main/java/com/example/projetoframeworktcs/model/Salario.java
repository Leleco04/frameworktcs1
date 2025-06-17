package com.example.projetoframeworktcs.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private Double calcularTaxaAliquota() {
        if (salarioBruto <= 2428.80) return 0.0;
        if (salarioBruto <= 2826.65) return 0.075;
        if (salarioBruto <= 3751.05) return 0.15;
        if (salarioBruto <= 4664.68) return 0.225;
        return 0.275;
    }

    public Double getVale() { return vale; }
    public void setVale(Double vale) { this.vale = vale; }
    public Double getPlanoSaude() { return planoSaude; }
    public void setPlanoSaude(Double planoSaude) { this.planoSaude = planoSaude; }
    public Double getPlanoOdontologico() { return planoOdontologico; }
    public void setPlanoOdontologico(Double planoOdontologico) { this.planoOdontologico = planoOdontologico; }
    public Double getBonusParticipacao() { return bonusParticipacao; }
    public void setBonusParticipacao(Double bonusParticipacao) { this.bonusParticipacao = bonusParticipacao; }
    public Double getTaxaAliquota() { return taxaAliquota; }
    public void setTaxaAliquota(Double taxaAliquota) { this.taxaAliquota = taxaAliquota; }
    public Double getSalarioBruto() { return salarioBruto; }
    public void setSalarioBruto(Double salarioBruto) { this.salarioBruto = salarioBruto; }
    public Double getSalarioLiquido() { return salarioLiquido; }
    public void setSalarioLiquido(Double salarioLiquido) { this.salarioLiquido = salarioLiquido; }
}
