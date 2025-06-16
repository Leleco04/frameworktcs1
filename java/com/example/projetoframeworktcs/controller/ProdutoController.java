package com.example.projetoframeworktcs.controller;

import com.example.projetoframeworktcs.model.Produto;
import com.example.projetoframeworktcs.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/produto")
public class ProdutoController {

    @Autowired
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Produto> addProduto(Produto produto) {
        Produto p = produtoService.adicionarProduto(produto);
        return ResponseEntity.ok(p);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Produto>> getProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    @DeleteMapping("/remover")
    public ResponseEntity<Void> deleteProduto(Long id) {
        produtoService.removerProduto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/produto_inicial")
    public String paginaInicialProdutos() {
        return "produto_inicial";
    }

    @GetMapping("/adicionar_produto")
    public String paginaAdicionarProduto() {
        return "adicionar_produto";
    }

}
