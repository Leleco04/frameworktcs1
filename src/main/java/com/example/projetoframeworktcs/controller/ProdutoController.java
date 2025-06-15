package com.example.projetoframeworktcs.controller;

import com.example.projetoframeworktcs.model.Produto;
import com.example.projetoframeworktcs.service.ProdutoService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
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
