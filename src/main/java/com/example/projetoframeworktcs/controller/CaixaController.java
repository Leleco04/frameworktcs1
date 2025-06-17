package com.example.projetoframeworktcs.controller;

import com.example.projetoframeworktcs.dto.ProdutoResponseDTO;
import com.example.projetoframeworktcs.model.Caixa;
import com.example.projetoframeworktcs.repository.ProdutoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CaixaController {

    private final ProdutoRepository produtoRepository;

    public CaixaController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/caixa")
    public String caixa(Model model) {
        model.addAttribute("caixa", Caixa.getValor());
        return "caixa";
    }
}
