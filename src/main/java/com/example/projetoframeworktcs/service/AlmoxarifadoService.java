package com.example.projetoframeworktcs.service;

import com.example.projetoframeworktcs.enumeracao.Categoria;
import com.example.projetoframeworktcs.model.Produto;
import com.example.projetoframeworktcs.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AlmoxarifadoService {

    @Autowired
    private ProdutoRepository produtoRepository;

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

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public boolean remover(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
