package com.example.projetoframeworktcs.service;

import com.example.projetoframeworktcs.dto.CriarProdutoDTO;
import com.example.projetoframeworktcs.dto.ProdutoResponseDTO;
import com.example.projetoframeworktcs.model.Categoria;
import com.example.projetoframeworktcs.model.Produto;
import com.example.projetoframeworktcs.repository.CategoriaRepository;
import com.example.projetoframeworktcs.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Categoria categoria = categoriaRepository.findById(dto.idCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));

        Produto produto = new Produto(dto.nome(), dto.descricao(), dto.valorCompra(), dto.valorVenda(), dto.qtdEstoque(), categoria);

        produtoRepository.save(produto);
    }

    public long quantidadeProdutos() {
        return produtoRepository.count();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));
    }

    public Produto adicionarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Page<ProdutoResponseDTO> listarProdutos(Pageable pageable) {
        Page<Produto> produtos = produtoRepository.findAll(pageable);

        return produtos.map(this::converterParaDTO);
    }

    public void removerProduto(Long id) {
        Produto p = buscarPorId(id);
        produtoRepository.delete(p);
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