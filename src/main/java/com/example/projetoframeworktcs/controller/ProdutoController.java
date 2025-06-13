package com.example.projetoframeworktcs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProdutoController {

    @GetMapping("/produto_inicial")
    public String paginaInicialProdutos() {
        return "produto_inicial";
    }

    @GetMapping("/adicionar_produto")
    public String paginaAdicionarProduto() {
        return "adicionar_produto";
    }

}
