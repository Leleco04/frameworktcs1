package com.example.projetoframeworktcs.service;

import com.example.projetoframeworktcs.model.Salario;
import org.springframework.stereotype.Service;

@Service
public class SalarioService {

    public Salario calcularSalario(Double vale, Double planoSaude, Double planoOdontologico, Double bonusParticipacao, Double taxaAliquota, Double salarioBruto){
        Salario salario = new Salario();
        double desconto = planoOdontologico + planoSaude + bonusParticipacao + vale + (salarioBruto * taxaAliquota);
        double salarioLiquido = salarioBruto + bonusParticipacao - desconto;
        salario.setSalarioLiquido(salarioLiquido);
        return salario;
    }
}
