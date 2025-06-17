package com.example.projetoframeworktcs.controller;

import com.example.projetoframeworktcs.model.Caixa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CaixaController {

    @GetMapping("/caixa")
    public String caixa(Model model) {
        model.addAttribute("caixa", Caixa.getValor());
        return "caixa";
    }
}
