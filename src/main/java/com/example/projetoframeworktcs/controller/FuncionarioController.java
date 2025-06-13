package com.example.projetoframeworktcs.controller;

import com.example.projetoframeworktcs.dto.AtualizarFuncionarioDTO;
import com.example.projetoframeworktcs.dto.FuncionarioDTO;
import com.example.projetoframeworktcs.model.Funcionario;
import com.example.projetoframeworktcs.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@RestController
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<Funcionario> addFuncionario(@RequestBody FuncionarioDTO funcionario) {
        Funcionario f = funcionarioService.adicionarFuncionario(funcionario);
        return ResponseEntity.ok(f);
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> getFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();
        return ResponseEntity.ok(funcionarios);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteFuncionario(Long id) {
        funcionarioService.removerFuncionario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Funcionario> updateFuncionario(Long id, AtualizarFuncionarioDTO dto) {
        Funcionario f = funcionarioService.atualizarFuncionario(id, dto);
        return ResponseEntity.ok(f);
    }
    
    @GetMapping("/funcionario_inicial")
    public String paginaInicialFuncionario() {
        return "funcionario_inicial";
    }

    @GetMapping("/adicionar_funcionario")
    public String paginaAdicionarFuncionario() {
        return "adicionar_funcionario";
    }

}


