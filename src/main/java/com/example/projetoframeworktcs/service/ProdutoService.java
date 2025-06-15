package com.example.projetoframeworktcs.service;

import com.example.projetoframeworktcs.model.Produto;
import com.example.projetoframeworktcs.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    /* public void criarProdutosIniciais() {
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
    } */

    public void registrarProduto(String nome, String descricao, double valorCompra, double valorVenda, int qtdEstoque) {
        Produto produto = new Produto(nome, descricao, valorCompra, valorVenda, qtdEstoque);

        produtoRepository.save(produto);
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));
    }

    public long quantidadeProdutos() {
        return produtoRepository.count();
    }

    public Produto adicionarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public void removerProduto(Long id) {
        Produto p = buscarPorId(id);
        produtoRepository.delete(p);
    }
}
