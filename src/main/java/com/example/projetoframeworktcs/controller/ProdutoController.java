package com.example.projetoframeworktcs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/produto")
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
