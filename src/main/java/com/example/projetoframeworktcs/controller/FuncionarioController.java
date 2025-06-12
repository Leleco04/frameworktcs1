package com.example.projetoframeworktcs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FuncionarioController {

    @GetMapping("/funcionario_inicial")
    public String paginaInicialFuncionario() {
        return "funcionario_inicial";
    }

    @GetMapping("/adicionar_funcionario")
    public String paginaAdicionarFuncionario() {
        return "adicionar_funcionario";
    }

    }