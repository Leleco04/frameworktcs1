package com.example.projetoframeworktcs.controller;

import com.example.projetoframeworktcs.dto.AtualizarFuncionarioDTO;
import com.example.projetoframeworktcs.model.Funcionario;
import com.example.projetoframeworktcs.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<Funcionario> addFuncionario(Funcionario funcionario) {
        Funcionario f = funcionarioService.adicionar(funcionario);
        return ResponseEntity.ok(f);
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> getFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.listar();
        return ResponseEntity.ok(funcionarios);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteFuncionario(Long id) {
        funcionarioService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Funcionario> updateFuncionario(Long id, AtualizarFuncionarioDTO dto) {
        Funcionario f = funcionarioService.atualizar(id, dto);
        return ResponseEntity.ok(f);
    }

}
