package com.example.projetoframeworktcs.service;

import com.example.projetoframeworktcs.dto.AtualizarFuncionarioDTO;
import com.example.projetoframeworktcs.dto.AtualizarProdutoDTO;
import com.example.projetoframeworktcs.dto.CriarProdutoDTO;
import com.example.projetoframeworktcs.dto.ProdutoResponseDTO;
import com.example.projetoframeworktcs.model.Categoria;
import com.example.projetoframeworktcs.model.Funcionario;
import com.example.projetoframeworktcs.model.Produto;
import com.example.projetoframeworktcs.repository.CategoriaRepository;
import com.example.projetoframeworktcs.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    /*
    public void criarProdutosIniciais() {
        if (produtoRepository.count() == 0) {
            String[] nomes = {"Pasta de dente\", \"Xarope\", \"Gel\", \"Shampoo\", \"Allegra\", \"Dipirona\", \"Proteína\", \"Coca-Cola", "Ibuprofeno", "Ledx", "Tarkov"};
            Random rand = new Random();

            for (String nome : nomes) {
                double valorCompra = 3 + rand.nextDouble() * 17; // 3 a 20
                int quantidade = 5 + rand.nextInt(295); // 5 a 300
                Produto p = new Produto();
                produtoRepository.save(p);
            }
        }
    }
    */

    // ***
    // Alterar name para th:field no arquivo .html adicionar
    // ***

    public void registrarProduto(CriarProdutoDTO dto) {
        if(dto.valorCompra() > dto.valorVenda()) {
            throw new RuntimeException("O valor de venda deve ser maior que o valor de compra.");
        } else if(dto.valorCompra() <= 0 || dto.valorVenda() <= 0) {
            throw new RuntimeException("Os valores devem ser maiores que 0.");
        } else {
            Categoria categoria = categoriaRepository.findById(dto.idCategoria())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));

            Produto produto = new Produto(dto.nome(), dto.descricao(), dto.valorCompra(), dto.valorVenda(), dto.qtdEstoque(), categoria);

            produtoRepository.save(produto);
        }
    }

    public long quantidadeProdutos() {
        return produtoRepository.count();
    }

    public Produto buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));
    }

    public Page<ProdutoResponseDTO> listarProdutos(Pageable pageable) {
        Page<Produto> produtos = produtoRepository.findAll(pageable);

        return produtos.map(this::converterParaDTO);
    }

    public AtualizarProdutoDTO atualizarProdutoPorId(Long id) {
        Produto p = buscarProdutoPorId(id);
        AtualizarProdutoDTO dto = new AtualizarProdutoDTO(p.getId(), p.getNome(), p.getDescricao(), p.getValorCompra(), p.getValorVenda(), p.getQtdEstoque(), p.getCategoria().getId());
        return dto;
    }

    public List<ProdutoResponseDTO> getProdutos() {
        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
                .map(produto -> new ProdutoResponseDTO(
                        produto.getId(), produto.getNome(), produto.getDescricao(), produto.getValorCompra(),
                        produto.getValorVenda(), produto.getQtdEstoque(), produto.getCategoria().getNome()
                ))
                .collect(Collectors.toList());
    }

    public void removerProduto(Long id) {
        Produto p = buscarProdutoPorId(id);
        produtoRepository.delete(p);
    }

    public Produto atualizarProduto(Long id, AtualizarProdutoDTO dto) {
        Produto produto = buscarProdutoPorId(id);

        Categoria categoria = categoriaRepository.findById(dto.getId_categoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));

        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setValorCompra(dto.getValorCompra());
        produto.setValorVenda(dto.getValorVenda());
        produto.setQtdEstoque(dto.getQtdEstoque());
        produto.setCategoria(categoria);

        return produtoRepository.save(produto);
    }

    private ProdutoResponseDTO converterParaDTO(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getValorCompra(),
                produto.getValorVenda(),
                produto.getQtdEstoque(),
                produto.getCategoria().getNome()
        );
    }
}
