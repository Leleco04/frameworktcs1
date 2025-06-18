/*
package com.example.projetoframeworktcs.service;

import com.example.projetoframeworktcs.model.Caixa;
import com.example.projetoframeworktcs.model.Negocio;
import com.example.projetoframeworktcs.model.enums.Status;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;

@Service
public class CaixaService {

    private Caixa caixa;
    private NegocioService negocioService;

    public void adicionarValor(Double valor) {
        caixa.setValor(Caixa.getValor() + valor);
    }

    public void removerValor(Double valor) {
        caixa.setValor(Caixa.getValor() - valor);
    }

    public Double estimarLucroMensal(Integer mes) {
        double saida = 0, entrada = 0;
        Month mesEscolhido = Month.of(mes);

        for(Negocio c : negocioService.listarCompras()) {
            // Verificar se a entrega está com status aberto
            if(c.getStatus() == Status.ABERTO && c.getDataProgramada() != null) {
                // Verificar se a entrega está com status aberto
                Month mesProgramado = c.getDataProgramada().getMonth();
                if(mesProgramado.equals(mesEscolhido)) {
                    saida += negocioService.calcularValorTotal(c);
                }
            }
        }

        for(Negocio v : negocioService.listarVendas()) {
            if(v.getStatus() == Status.ABERTO && v.getDataProgramada() != null) {
                // Verificar se a entrega está com status aberto
                Month mesProgramado = v.getDataProgramada().getMonth();
                if(mesProgramado.equals(mesEscolhido)) {
                    entrada += negocioService.calcularValorTotal(v);
                }
            }
        }
        return entrada - saida;
    }

    public Double getLucroMensal() {
        Month mesAtual = LocalDate.now().getMonth();
        double entrada = 0, saida = 0;

        for (Negocio c : negocioService.listarCompras()) {
            if (c.getStatus() == Status.FINALIZADO) {
                LocalDateTime dataFinal = c.getDataFinalizacao() != null ? c.getDataFinalizacao() : c.getDataNegocio();
                if (dataFinal.getMonth() == mesAtual) {
                    saida += negocioService.calcularValorTotal(c);
                }
            }
        }

        for (Negocio v : negocioService.listarVendas()) {
            if (v.getStatus() == Status.FINALIZADO) {
                LocalDateTime dataFinal = v.getDataFinalizacao() != null ? v.getDataFinalizacao() : v.getDataNegocio();
                if (dataFinal.getMonth() == mesAtual) {
                    entrada += negocioService.calcularValorTotal(v);
                }
            }
        }
        return entrada - saida;
    }

    public Double estimarLucroAnual(Integer ano) {
        double saida = 0, entrada = 0;
        Year anoEscolhido = Year.of(ano);

        for(Negocio c : negocioService.listarCompras()) {
            if(c.getStatus() == Status.ABERTO && c.getDataProgramada() != null) {
                // Verificar se a entrega está com status aberto
                Year anoProgramado = Year.of(c.getDataProgramada().getYear());
                if(anoProgramado.equals(anoEscolhido)) {
                    saida += negocioService.calcularValorTotal(c);
                }
            }
        }

        for(Negocio v : negocioService.listarVendas()) {
            if(v.getStatus().equals(Status.ABERTO) && v.getDataProgramada() != null) {
                // Verificar se a entrega está com status aberto
                Year anoProgramado = Year.of(v.getDataProgramada().getYear());
                if(anoProgramado.equals(anoEscolhido)) {
                    entrada += negocioService.calcularValorTotal(v);
                }
            }
        }
        return entrada - saida;
    }
}
*/