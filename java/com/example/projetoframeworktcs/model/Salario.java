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


    //    EXEMPLO DE COMO DEVE SER UTILIZADO
//    @RestController
//    public class FolhaPagamentoController {
//
//        @Autowired // O Spring injeta a instância do nosso serviço
//        private SalarioService.java salarioService;
//
//        @Autowired
//        private FuncionarioRepository funcionarioRepo; // Exemplo
//
//        @PostMapping("/funcionario/{id}/calcular-salario")
//        public Salario calcular(@PathVariable Long id) {
//            Funcionario funcionario = funcionarioRepo.findById(id).orElseThrow();
//            Caixa caixa = new Caixa(); // Obter o caixa de algum lugar
//            double salarioBruto = funcionario.getSalarioBruto(); // Exemplo
//
//            // Chama o serviço, que faz todo o trabalho pesado
//            Salario detalhesSalario = salarioService.calcularSalarioCompleto(funcionario, salarioBruto, caixa);
//
//            // Retorna o objeto de dados para quem chamou (ex: para o frontend)
//            return detalhesSalario;
//        }
}
