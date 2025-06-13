package controller;

import model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.FuncionarioService;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<Funcionario> criarFuncionario(@RequestParam String nome, 
                                                        @RequestParam String sobrenome, 
                                                        @RequestParam String codigoFuncionario, 
                                                        @RequestParam int idade, 
                                                        @RequestParam int genero, 
                                                        @RequestParam Setor setor) {
        Funcionario funcionario = funcionarioService.criarFuncionario(nome, sobrenome, codigoFuncionario, idade, genero, setor);
        if (funcionario == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(funcionario);
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> listarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();
        return ResponseEntity.ok(funcionarios);
    }
}
