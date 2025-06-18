package com.example.projetoframeworktcs.controller;

import com.example.projetoframeworktcs.model.Caixa;
import com.example.projetoframeworktcs.service.NegocioService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CaixaController {

    private final NegocioService negocioService;

    public CaixaController(NegocioService negocioService) {
        this.negocioService = negocioService;
    }

    @GetMapping("/caixa")
    public String caixa(Model model, @PageableDefault(size = 5, sort = "id") Pageable pageable) {
        model.addAttribute("negocios", negocioService.getNegocios(pageable));
        model.addAttribute("qtdNegocios", negocioService.quantidadeNegocios());
        return "caixa";
    }

    @GetMapping("/relatorio")
    public String relatorio(Model model) {
        model.addAttribute("caixa", Caixa.getValor());
        model.addAttribute("lucroAnualProgramado", negocioService.estimarLucroAnual());
        model.addAttribute("lucroMensalProgramado", negocioService.estimarLucroMensal());
        return "relatorio";
    }
}
