package com.example.projetoframeworktcs.service;

import com.example.projetoframeworktcs.model.Caixa;
import com.example.projetoframeworktcs.model.Funcionario;
import com.example.projetoframeworktcs.model.Salario;
import com.example.projetoframeworktcs.model.Setor; // Supondo o import
import org.springframework.stereotype.Service;

@Service
public class SalarioService {

    public Salario calcularSalarioCompleto(Funcionario funcionario, double salarioBruto, Caixa caixa) {

        double vale = calcularVale(funcionario);
        double planoSaude = calcularPlanoSaude(funcionario);
        double planoOdontologico = 3000.0;
        double bonus = calcularBonus(caixa);
        double taxaAliquota = calcularTaxaAliquota(salarioBruto);

        double desconto = vale + (salarioBruto * taxaAliquota);
        double salarioLiquido = salarioBruto + bonus - desconto;

        funcionario.setSalario(salarioLiquido);
        System.out.println("Salario calculado e definido: R$" + salarioLiquido + "\n");

        return new Salario(vale, planoSaude, planoOdontologico, bonus, taxaAliquota, salarioBruto);
    }

    private double calcularPlanoSaude(Funcionario funcionario) {
        Setor setor = funcionario.getSetor();
        if (setor == null || setor.getNome() == null) {
            return 0;
        }

        switch (setor.getNome().toLowerCase()) {
            case "vendas":
            case "atendimento ao cliente":
                return 3000;
            case "almoxarifado":
                return 3500;
            case "financeiro":
                return 3750;
            case "gestão de pessoas":
                return 4200;
            case "gerente de filial":
                return 5000;
            default:
                return 0;
        }
    }

    private double calcularVale(Funcionario funcionario) {
        Setor setor = funcionario.getSetor();
        if (setor == null || setor.getNome() == null) {
            return 300;
        }

        switch (setor.getNome().toLowerCase()) {
            case "almoxarifado":
                return 360;
            case "financeiro":
                return 400;
            case "gestão de pessoas":
                return 520;
            case "gerente de filial":
                return 1000;
            case "vendas":
            case "atendimento ao cliente":
            default:
                return 300;
        }
    }

    private double calcularTaxaAliquota(double salarioBruto) {
        if (salarioBruto <= 2428.80) return 0;
        if (salarioBruto <= 2826.65) return 0.075;
        if (salarioBruto <= 3751.05) return 0.15;
        if (salarioBruto <= 4664.68) return 0.225;
        return 0.275;
    }

    private double calcularBonus(Caixa caixa) {
        return caixa.getLucroMensal();
    }
}