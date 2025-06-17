package com.example.projetoframeworktcs.service;

import com.example.projetoframeworktcs.dto.AtualizarNegocioDTO;
import com.example.projetoframeworktcs.dto.CriarNegocioDTO;
import com.example.projetoframeworktcs.model.Funcionario;
import com.example.projetoframeworktcs.model.ItemNegocio;
import com.example.projetoframeworktcs.model.Produto;
import com.example.projetoframeworktcs.model.enums.Status;
import com.example.projetoframeworktcs.model.enums.TipoNegocio;
import com.example.projetoframeworktcs.model.Negocio;
import com.example.projetoframeworktcs.repository.FuncionarioRepository;
import com.example.projetoframeworktcs.repository.NegocioRepository;
import com.example.projetoframeworktcs.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NegocioService {

    private final ProdutoRepository produtoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private NegocioRepository negocioRepository;

    public NegocioService(ProdutoRepository produtoRepository, FuncionarioRepository funcionarioRepository) {
        this.produtoRepository = produtoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    //private CaixaService caixaService;

    /*
    public Negocio adicionarNegocio(Negocio negocio) {
        Double valorTotal = calcularValorTotal(negocio);
        Negocio n = new Negocio(valorTotal, negocio.getStatus(), negocio.getFuncionariosEnvolvidos(), negocio.getListaProdutos(), negocio.getDataProgramada(), negocio.getTipo(), negocio.getTransportadora());

        if(n.getTipo().equals(TipoNegocio.VENDA)) caixaService.adicionarValor(valorTotal);
        else caixaService.removerValor(valorTotal);

        return negocioRepository.save(n);
    }
    */

    public List<Negocio> listarNegocios() { return negocioRepository.findAll(); }

    public List<Negocio> listarVendas() { return negocioRepository.findByTipo(TipoNegocio.VENDA); }

    public List<Negocio> listarCompras() { return negocioRepository.findByTipo(TipoNegocio.COMPRA); }

    public List<Negocio> listarNegociosAbertos() { return negocioRepository.findByStatus(Status.ABERTO); }

    public Negocio buscarNegocioPorId(Long id) {
        return negocioRepository.findById(id).orElseThrow( () -> new RuntimeException("Negócio não encontrado."));
    }

    @Transactional
    public void criar(CriarNegocioDTO dto) {
        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        Negocio negocio = new Negocio();
        negocio.setTipo(dto.getTipo());
        negocio.setProduto(produto);
        negocio.setStatus(dto.getStatus());
        negocio.setQuantidade(dto.getQuantidade());

        if(dto.getTipo().equals("venda")) {
            negocio.setValorNegocio(produto.getValorVenda() * negocio.getQuantidade());
        } else {
            negocio.setValorNegocio(produto.getValorCompra() * negocio.getQuantidade());
        }

        if(negocio.getStatus().equals("aberto")) {
            negocio.setDataProgramada(dto.getDataProgramada());
        }

        for(Long funcionarioId : dto.getFuncionarioIds()) {
            Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                    .orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));
            negocio.getFuncionarios().add(funcionario);
        }

        negocioRepository.save(negocio);
    }

//    public void removerNegocio(Long id) {
//        Negocio n = buscarNegocioPorId(id);
//
//        if(n.getTipo().equals(TipoNegocio.VENDA)) caixaService.removerValor(calcularValorTotal(n));
//        else caixaService.adicionarValor(calcularValorTotal(n));
//
//        negocioRepository.delete(n);
//    }
//
//    public Negocio atualizarNegocio(Long id, AtualizarNegocioDTO dto) {
//        Negocio negocio = buscarNegocioPorId(id);
//
//        negocio.setListaProdutos(dto.getListaProdutos());
//        negocio.setFuncionariosEnvolvidos(dto.getFuncionariosEnvolvidos());
//        negocio.setValorNegocio(calcularValorTotal(negocio));
//        negocio.setStatus(dto.getStatus());
//        negocio.setDataProgramada(dto.getDataProgramada());
//        negocio.setTipo(dto.getTipo());
//        negocio.setTransportadora(dto.getTransportadora());
//
//        return negocioRepository.save(negocio);
//    }
//
//    public Double calcularValorTotal(Negocio negocio) {
//        Double soma = 0.0;
//        if(negocio.getTipo().equals(TipoNegocio.VENDA)) {
//            for(ItemNegocio item : negocio.getListaProdutos()) {
//                soma += item.getProduto().getValorVenda() * item.getQtd();
//            }
//        } else {
//            for(ItemNegocio item : negocio.getListaProdutos()) {
//                soma += item.getProduto().getValorCompra() * item.getQtd();
//            }
//        }
//        soma += negocio.getTransportadora().getValorFreteFixo();
//        return soma;
//    }

    /*
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
     */
}