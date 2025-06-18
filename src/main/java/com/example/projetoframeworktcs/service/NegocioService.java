package com.example.projetoframeworktcs.service;

import com.example.projetoframeworktcs.dto.AtualizarNegocioDTO;
import com.example.projetoframeworktcs.model.ItemNegocio;
import com.example.projetoframeworktcs.model.enums.Status;
import com.example.projetoframeworktcs.model.enums.TipoNegocio;
import com.example.projetoframeworktcs.model.Negocio;
import com.example.projetoframeworktcs.repository.NegocioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NegocioService {

    private NegocioRepository negocioRepository;
    private CaixaService caixaService;

    public Negocio adicionarNegocio(Negocio negocio) {
        Double valorTotal = calcularValorTotal(negocio);
        Negocio n = new Negocio(valorTotal, negocio.getStatus(), negocio.getFuncionariosEnvolvidos(), negocio.getListaProdutos(), negocio.getDataProgramada(), negocio.getTipo(), negocio.getTransportadora());

        if(n.getTipo().equals(TipoNegocio.VENDA)) caixaService.adicionarValor(valorTotal);
        else caixaService.removerValor(valorTotal);

        return negocioRepository.save(n);
    }

    public List<Negocio> listarNegocios() { return negocioRepository.findAll(); }

    public List<Negocio> listarVendas() { return negocioRepository.findByTipo(TipoNegocio.VENDA); }

    public List<Negocio> listarCompras() { return negocioRepository.findByTipo(TipoNegocio.COMPRA); }

    public List<Negocio> listarNegociosAbertos() { return negocioRepository.findByStatus(Status.ABERTO); }

    public Negocio buscarNegocioPorId(Long id) {
        return negocioRepository.findById(id).orElseThrow( () -> new RuntimeException("Negócio não encontrado."));
    }

    public void removerNegocio(Long id) {
        Negocio n = buscarNegocioPorId(id);

        if(n.getTipo().equals(TipoNegocio.VENDA)) caixaService.removerValor(calcularValorTotal(n));
        else caixaService.adicionarValor(calcularValorTotal(n));

        negocioRepository.delete(n);
    }

    public Negocio atualizarNegocio(Long id, AtualizarNegocioDTO dto) {
        Negocio negocio = buscarNegocioPorId(id);

        negocio.setListaProdutos(dto.getListaProdutos());
        negocio.setFuncionariosEnvolvidos(dto.getFuncionariosEnvolvidos());
        negocio.setValorNegocio(calcularValorTotal(negocio));
        negocio.setStatus(dto.getStatus());
        negocio.setDataProgramada(dto.getDataProgramada());
        negocio.setTipo(dto.getTipo());
        negocio.setTransportadora(dto.getTransportadora());

        return negocioRepository.save(negocio);
    }

    public Double calcularValorTotal(Negocio negocio) {
        Double soma = 0.0;
        if(negocio.getTipo().equals(TipoNegocio.VENDA)) {
            for(ItemNegocio item : negocio.getListaProdutos()) {
                soma += item.getProduto().getValorVenda() * item.getQtd();
            }
        } else {
            for(ItemNegocio item : negocio.getListaProdutos()) {
                soma += item.getProduto().getValorCompra() * item.getQtd();
            }
        }
        soma += negocio.getTransportadora().getValorFreteFixo();
        return soma;
    }

//    public void finalizarNegocioAberto(Negocio negocio) throws EstoqueInsuficienteException {
//        if (negocio == null || negocio.getStatus() != Status.ABERTO) {
//            return;
//        }
//
//        if (negocio.getTipo() == TipoNegocio.VENDA) {
//            for (ItemNegocio item : negocio.getListaProdutos()) {
//                if (item.getProduto().getQtdEstoque() < item.getQtd()) {
//                    throw new EstoqueInsuficienteException("Estoque insuficiente para finalizar a venda de " + item.getProduto().getNome());
//                }
//            }
//            caixaService.adicionarValor(calcularValorTotal(negocio));
//            for (ItemNegocio item : negocio.getListaProdutos()) {
//                item.getProduto().removeEstoque(item.getQtd());
//            }
//
//        } else if (negocio.getTipo() == TipoNegocio.COMPRA) {
//            caixaService.removerValor(calcularValorTotal(negocio));
//            for (ItemNegocio item : negocio.getListaProdutos()) {
//                item.getProduto().addEstoque(item.getQtd());
//            }
//        }
//
//        negocio.setStatus(Status.FINALIZADO);
//        negocio.setDataFinalizacao(LocalDateTime.now());
//    }

    public String exibirDados(Negocio negocio) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("TIPO DE NEGÓCIO: ").append(negocio.getTipo()).append("\n");
        sb.append("   - Status: ").append(negocio.getStatus()).append("\n");
        sb.append("   - Data de Registro: ").append(negocio.getDataProgramadaFormatada()).append("\n");
        sb.append("   - Transportadora: ").append(negocio.getTransportadora()).append("\n");

        // Adiciona a data programada apenas se ela existir
        if (negocio.getDataProgramada() != null) {
            sb.append("   - Data Programada: ").append(negocio.getDataProgramadaFormatada()).append("\n");
        }

        sb.append("\n   --- Itens do Negócio ---\n");
        for (ItemNegocio item : negocio.getListaProdutos()) {
            sb.append(String.format("   - Produto: %-25s | Qtd: %-5d | Valor Unit.: R$ %.2f\n",
                    item.getProduto().getNome(),
                    item.getQtd(),
                    (negocio.getTipo() == TipoNegocio.VENDA ? item.getProduto().getValorVenda() : item.getProduto().getValorCompra())
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
