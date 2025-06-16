package com.example.projetoframeworktcs.controller;

import com.example.projetoframeworktcs.model.Produto;
import com.example.projetoframeworktcs.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
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
    public String paginaInicialProdutos(Model model) {
        List<Produto> produtos = produtoService.listarProdutos();
        String quantidade = produtoService.quantidadeProdutos() + " produto(s)";
        model.addAttribute("quantidade", quantidade);
        model.addAttribute("produtos", produtos);
        return "produto_inicial";
    }

    @GetMapping("/adicionar_produto")
    public String paginaAdicionarProduto() {
        return "adicionar_produto";
    }

    @PostMapping("/produtos/adicionar")
    public String registraFuncionario(
            @RequestParam String nome, @RequestParam String descricao,
            @RequestParam double valor_compra, @RequestParam double valor_venda, @RequestParam int estoque,
            RedirectAttributes redirectAttributes
    ) {
        try {
            produtoService.registrarProduto(nome, descricao, valor_compra, valor_venda, estoque);
            redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");
            return "redirect:/produto_inicial";
        } catch(DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("erro", "Erro: o valor de venda deve ser maior que o de compra.");
            return "redirect:/adicionar_produto";
        }
    }

}
