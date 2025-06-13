package com.example.projetoframeworktcs.service;

import com.example.projetoframeworktcs.dto.AtualizarNegocioDTO;
import com.example.projetoframeworktcs.model.enums.TipoNegocio;
import com.example.projetoframeworktcs.model.Negocio;
import com.example.projetoframeworktcs.repository.NegocioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NegocioService {

    @Autowired
    private NegocioRepository negocioRepository;

    public Negocio adicionarNegocio(Negocio negocio) {
        return negocioRepository.save(negocio);
    }

    public List<Negocio> listarNegocios() {
        return negocioRepository.findAll();
    }

    public void removerNegocio(Long id) {
        Negocio n = negocioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Negócio não encontrado."));
        negocioRepository.delete(n);
    }

    public Negocio atualizarNegocio(Long id, AtualizarNegocioDTO dto) {
        Negocio negocio = negocioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Negócio não encontrado."));

        negocio.setStatus(dto.getStatus());
        negocio.setFuncionariosEnvolvidos(dto.getFuncionariosEnvolvidos());
        negocio.setId_produto(dto.getId_produto());
        negocio.setDataProgramada(dto.getDataProgramada());
        negocio.setTipo(dto.getTipo());

        return negocioRepository.save(negocio);
    }

    public double calcularValorTotal(Negocio negocio) {
        double soma = 0;
        if(negocio.getTipo().equals(TipoNegocio.VENDA)) {
            for(ItemNegocio item : produtos) {
                soma += item.getProduto().getValorVenda() * item.getQtd();
            }
        } else {
            for(ItemNegocio item : produtos) {
                soma += item.getProduto().getValorCompra() * item.getQtd();
            }
        }
        return soma;
    }

    public String exibirDados(Negocio negocio) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("TIPO DE NEGÓCIO: ").append(negocio.getTipo()).append("\n");
        sb.append("   - Status: ").append(negocio.getStatus()).append("\n");
        sb.append("   - Data de Registro: ").append(negocio.getDataProgramadaFormatada()).append("\n");

        // Adiciona a data programada apenas se ela existir
        if (negocio.getDataProgramada() != null) {
            sb.append("   - Data Programada: ").append(negocio.getDataProgramadaFormatada()).append("\n");
        }

        sb.append("\n   --- Itens do Negócio ---\n");
        for (ItemNegocio item : this.produtos) {
            sb.append(String.format("   - Produto: %-25s | Qtd: %-5d | Valor Unit.: R$ %.2f\n",
                    item.getProduto().getNome(),
                    item.getQtd(),
                    (tipo == TipoNegocio.VENDA ? item.getProduto().getValorVenda() : item.getProduto().getValorCompra())
            ));
        }

        // Adiciona o valor total formatado como moeda
        sb.append(String.format("\n   VALOR TOTAL DA " + String.valueOf(negocio.getTipo()).toUpperCase() + ": R$ %.2f\n", negocio.getValorNegocio()));

        // Adiciona os funcionários apenas se a lista não for nula ou vazia
        if (negocio.getFuncionariosEnvolvidos() != null && !negocio.getFuncionariosEnvolvidos().isEmpty()) {
            sb.append("\n   --- Funcionários Envolvidos ---\n");
            for(String funcionario : negocio.getFuncionariosEnvolvidos()) {
                sb.append("   - ").append(funcionario).append("\n");
            }
        }

        sb.append("----------------------------------------\n");

        return sb.toString();
    }

}
