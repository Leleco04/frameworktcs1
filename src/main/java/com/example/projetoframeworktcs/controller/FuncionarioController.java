package com.example.projetoframeworktcs.controller;

import com.example.projetoframeworktcs.model.Funcionario;
import com.example.projetoframeworktcs.model.Setor;
import com.example.projetoframeworktcs.repository.SetorRepository;
import com.example.projetoframeworktcs.service.FuncionarioService;
import com.example.projetoframeworktcs.service.SetorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Arrays;

@Controller
public class FuncionarioController {

    private final SetorService setorService;
    private final FuncionarioService funcionarioService;

    public FuncionarioController(SetorService setorService, FuncionarioService funcionarioService) {
        this.setorService = setorService;
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("/funcionario_inicial")
    public String paginaInicialFuncionario(Model model) {
        long contagem = funcionarioService.quantidadeFuncionarios();
        List<Funcionario> funcionarios = funcionarioService.getFuncionarios();
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("qtdFuncionarios", String.valueOf(contagem) + " resultados");
        return "funcionario_inicial";
    }

    @GetMapping("/adicionar_funcionario")
    public String paginaAdicionarFuncionario(Model model) {
        List<Setor> setores = setorService.getSetores();
        model.addAttribute("setores", setores);
        return "adicionar_funcionario";
    }

    @PostMapping("/funcionarios/adicionar")
    public String registraFuncionario(@RequestParam String nome, @RequestParam String sobrenome, @RequestParam int idade, @RequestParam String genero, @RequestParam long setor) {
        funcionarioService.registrarFuncionario(nome, sobrenome, genero, idade, setor);
        return "redirect:/funcionario_inicial";
    }

    }