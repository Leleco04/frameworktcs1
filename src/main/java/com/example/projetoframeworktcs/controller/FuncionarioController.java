package com.example.projetoframeworktcs.controller;

import com.example.projetoframeworktcs.model.Funcionario;
import com.example.projetoframeworktcs.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
