package com.example.projetoframeworktcs.controller;

import com.example.projetoframeworktcs.dto.AtualizarFuncionarioDTO;
import com.example.projetoframeworktcs.dto.FuncionarioDTO;
import com.example.projetoframeworktcs.model.Funcionario;
import com.example.projetoframeworktcs.model.Setor;
import com.example.projetoframeworktcs.service.FuncionarioService;
import com.example.projetoframeworktcs.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FuncionarioController {

    private final SetorService setorService;
    private final FuncionarioService funcionarioService;

    public FuncionarioController(SetorService setorService, FuncionarioService funcionarioService) {
        this.setorService = setorService;
        this.funcionarioService = funcionarioService;
    }

    /* @PostMapping("/adicionar")
    public ResponseEntity<Funcionario> addFuncionario(@RequestBody FuncionarioDTO funcionario) {
        Funcionario f = funcionarioService.adicionarFuncionario(funcionario);
        return ResponseEntity.ok(f);
    } */
    @GetMapping("/listar")
    public ResponseEntity<List<Funcionario>> getFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();
        return ResponseEntity.ok(funcionarios);
    }

    @DeleteMapping("/remover")
    public ResponseEntity<Void> deleteFuncionario(Long id) {
        funcionarioService.removerFuncionario(id);
        return ResponseEntity.noContent().build();
    }

    /* @PutMapping("/atualizar")
    public ResponseEntity<Funcionario> updateFuncionario(Long id, AtualizarFuncionarioDTO dto) {
        Funcionario f = funcionarioService.atualizarFuncionario(id, dto);
        return ResponseEntity.ok(f);
    } */
    
    @GetMapping("/funcionario_inicial")
    public String paginaInicialFuncionario(Model model) {
        String qtdFuncionarios = funcionarioService.quantidadeFuncionarios() + " funcionário(s)";
        List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("qtdFuncionarios", qtdFuncionarios);
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


