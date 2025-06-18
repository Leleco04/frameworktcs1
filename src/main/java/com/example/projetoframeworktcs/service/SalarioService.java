package com.example.projetoframeworktcs.service;

import com.example.projetoframeworktcs.model.Funcionario;
import com.example.projetoframeworktcs.model.Salario;
import com.example.projetoframeworktcs.model.Setor;
import org.springframework.stereotype.Service;

@Service
public class SalarioService {

    public Salario calcularSalarioCompleto(Funcionario funcionario) {
        double salarioBruto = funcionario.getSetor().getSalarioBase();
        double salarioLiquido = calcularDesconto(salarioBruto);

        Salario salario = new Salario();
        salario.setVale(calcularVale(funcionario));
        salario.setPlanoSaude(calcularPlanoSaude(funcionario));
        salario.setSalarioBruto(salarioBruto);
        salario.setSalarioLiquido(salarioLiquido);
        salario.setPlanoOdontologico(calcularPlanoOdonto(funcionario));

        return salario;
    }

    private Double calcularDesconto(Double salarioBruto) {
        double aliquota = 0;

        if (salarioBruto == null || salarioBruto <= 2428.80) {
            return salarioBruto;
        } else if(salarioBruto <= 2826.65) {
            aliquota = 0.075;
        } else if (salarioBruto <= 3751.05) {
            aliquota = 0.15;
        } else if (salarioBruto <= 4664.68) {
            aliquota = 0.225;
        } else {
            aliquota = 0.275;
        }

        return salarioBruto - (salarioBruto * aliquota);
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

    private Double calcularPlanoOdonto(Funcionario funcionario) {
        Setor setor = funcionario.getSetor();
        if (setor == null) {
            return 0.0;
        }

        Long id = setor.getId();

        if(id == 1 || id == 5) return 1000.0;
        else if(id == 2) return 1500.0;
        else if(id == 3) return 2000.0;
        else if(id == 4) return 2500.0;
        else if(id == 6) return 3000.0;
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


