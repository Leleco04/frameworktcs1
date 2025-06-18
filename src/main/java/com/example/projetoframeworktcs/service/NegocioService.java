package com.example.projetoframeworktcs.service;

import com.example.projetoframeworktcs.dto.AtualizarNegocioDTO;
import com.example.projetoframeworktcs.dto.CriarNegocioDTO;
import com.example.projetoframeworktcs.dto.FuncionarioResponseDTO;
import com.example.projetoframeworktcs.dto.NegocioResponseDTO;
import com.example.projetoframeworktcs.model.*;
import com.example.projetoframeworktcs.model.enums.Status;
import com.example.projetoframeworktcs.model.enums.TipoNegocio;
import com.example.projetoframeworktcs.repository.FuncionarioRepository;
import com.example.projetoframeworktcs.repository.NegocioRepository;
import com.example.projetoframeworktcs.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Service
public class NegocioService {

    private final ProdutoRepository produtoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final NegocioRepository negocioRepository;

    public NegocioService(ProdutoRepository produtoRepository, FuncionarioRepository funcionarioRepository, NegocioRepository negocioRepository) {
        this.produtoRepository = produtoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.negocioRepository = negocioRepository;
    }

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

    public List<Negocio> listarVendas() {
        return negocioRepository.findByTipo("venda");
    }

    public List<Negocio> listarCompras() {
        return negocioRepository.findByTipo("compra");
    }

    public List<Negocio> listarNegociosAbertos() {
        return negocioRepository.findByStatus("aberto");
    }

    public List<Negocio> listarNegociosFinalizados() {
        return negocioRepository.findByStatus("finalizado");
    }

    public Negocio buscarNegocioPorId(Long id) {
        return negocioRepository.findById(id).orElseThrow( () -> new RuntimeException("Negócio não encontrado."));
    }

    public Page<NegocioResponseDTO> getNegocios(Pageable pageable) {
        Page<Negocio> negocios = negocioRepository.findAll(pageable);

        return negocios.map(this::converterNegocioParaDTO);
    }

    public double estimarLucroMensal() {
        int mesAtual = LocalDate.now().getMonthValue();

        List<Negocio> negociosAgendados = negocioRepository.findByStatus("aberto");

        double somaCompra = 0;
        double somaVenda = 0;

        for(Negocio negocio : negociosAgendados) {
            if(negocio.getDataProgramada().getMonthValue() == mesAtual) {
                if(negocio.getTipo().equals("compra")) {
                    somaCompra += negocio.getValorNegocio();
                } else {
                    somaVenda += negocio.getValorNegocio();
                }
            }
        }

        return somaVenda - somaCompra;
    }

    public double estimarLucroAnual() {
        int anoAtual = LocalDate.now().getYear();

        List<Negocio> negociosAgendados = negocioRepository.findByStatus("aberto");

        double somaCompra = 0;
        double somaVenda = 0;

        for(Negocio negocio : negociosAgendados) {
            if(negocio.getDataProgramada().getYear() == anoAtual) {
                if(negocio.getTipo().equals("compra")) {
                    somaCompra += negocio.getValorNegocio();
                } else {
                    somaVenda += negocio.getValorNegocio();
                }
            }
        }
        return somaVenda - somaCompra;
    }

    public void criar(CriarNegocioDTO dto) {
        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        Negocio negocio = new Negocio();
        negocio.setTipo(dto.getTipo());
        negocio.setProduto(produto);
        negocio.setStatus(dto.getStatus());
        negocio.setQuantidade(dto.getQuantidade());
        negocio.setTransportadora(dto.getTransportadora());

        if(dto.getTipo().equals("venda")) {
            if(negocio.getQuantidade() <= produto.getQtdEstoque()) {
                double valor = produto.getValorVenda() * negocio.getQuantidade();
                negocio.setValorNegocio(valor);
                produto.removeEstoque(negocio.getQuantidade());
                Caixa.aumentarValor(valor);
            } else {
                throw new RuntimeException("Produto sem estoque suficiente.");
            }
        } else {
            if(negocio.getQuantidade() <= 0) {
                throw new RuntimeException("A quantidade de compra deve ser maior que 0.");
            } else {
                double valor = produto.getValorCompra() * negocio.getQuantidade();
                negocio.setValorNegocio(valor);
                produto.addEstoque(negocio.getQuantidade());
                Caixa.diminuirValor(valor);
            }
        }

        produtoRepository.save(produto);

        if(negocio.getStatus().equals("aberto")) {
            if(dto.getDataProgramada() == null) {
                throw new RuntimeException("Um negócio agendado deve ter uma data programada.");
            } else if(dto.getDataProgramada().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Data inválida.");
            } else {
                negocio.setDataProgramada(dto.getDataProgramada());
            }
        }

        for(Long funcionarioId : dto.getFuncionarioIds()) {
            Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                    .orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));
            negocio.getFuncionarios().add(funcionario);
        }

        negocioRepository.save(negocio);
    }

    public long quantidadeNegocios() {
        return negocioRepository.count();
    }

    private NegocioResponseDTO converterNegocioParaDTO(Negocio negocio) {
        List<FuncionarioResponseDTO> funcionariosDTO = negocio.getFuncionarios().stream()
                .map(funcionario -> new FuncionarioResponseDTO(
                        funcionario.getId(), funcionario.getNome(), funcionario.getSobrenome(), funcionario.getGenero(),
                        funcionario.getIdade(), funcionario.getSetor().getNome(), funcionario.getSalario().getSalarioLiquido()
                ))
                .toList();

        return new NegocioResponseDTO(
                negocio.getId(),
                negocio.getStatus(),
                negocio.getDataProgramada(),
                negocio.getDataNegocio(),
                negocio.getValorNegocio(),
                negocio.getTipo(),
                negocio.getTransportadora(),
                negocio.getQuantidade(),
                negocio.getProduto().getNome(),
                funcionariosDTO
        );
    }

    /*
    public void removerNegocio(Long id) {
        Negocio n = buscarNegocioPorId(id);

        if(n.getTipo().equals(TipoNegocio.VENDA)) caixaService.removerValor(calcularValorTotal(n));
        else caixaService.adicionarValor(calcularValorTotal(n));

        negocioRepository.delete(n);
    }
    */

    /*
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
    */

    /*
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
    */

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
