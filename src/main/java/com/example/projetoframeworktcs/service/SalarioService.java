package com.example.projetoframeworktcs.service;

import com.example.projetoframeworktcs.model.Funcionario;
import com.example.projetoframeworktcs.model.Salario;
import com.example.projetoframeworktcs.model.Setor;
import org.springframework.stereotype.Service;

@Service
public class SalarioService {

    private CaixaService caixaService;

    public Salario calcularSalarioCompleto(Funcionario funcionario, Double salarioBruto) {

        Double vale = calcularVale(funcionario);
        Double planoSaude = calcularPlanoSaude(funcionario);
        Double planoOdontologico = 3000.0;
        Double bonus = caixaService.getLucroMensal();
        Double taxaAliquota = funcionario.getSalario().getTaxaAliquota();

        Double desconto = vale + (salarioBruto * taxaAliquota);
        Double salarioLiquido = salarioBruto + bonus - desconto;

        Salario salario = new Salario(vale, planoSaude, planoOdontologico, bonus, salarioBruto, salarioLiquido);
        funcionario.setSalario(salario);
        return new Salario(vale, planoSaude, planoOdontologico, bonus, salarioBruto, salarioLiquido);
    }

    private Double calcularPlanoSaude(Funcionario funcionario) {
        Setor setor = funcionario.getSetor();
        if (setor == null) {
            return 0.0;
        }

        Long id = setor.getId();

        if(id == 1 || id == 5) return 3000.0;
        else if(id == 2) return 3750.0;
        else if(id == 3) return 4200.0;
        else if(id == 4) return 5000.0;
        else if(id == 6) return 3500.0;
        else return 0.0;
    }

    private Double calcularVale(Funcionario funcionario) {
        Setor setor = funcionario.getSetor();
        if (setor == null) {
            return 300.0;
        }

        Long id = setor.getId();

        if(id == 1 || id == 5) return 300.0;
        else if(id == 2) return 400.0;
        else if(id == 3) return 520.0;
        else if(id == 4) return 1000.0;
        else if(id == 6) return 360.0;
        else return 300.0;
    }
}