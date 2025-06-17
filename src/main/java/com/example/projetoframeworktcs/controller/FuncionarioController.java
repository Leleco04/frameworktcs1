package com.example.projetoframeworktcs.controller;

import com.example.projetoframeworktcs.dto.AtualizarFuncionarioDTO;
import com.example.projetoframeworktcs.dto.CriarFuncionarioDTO;
import com.example.projetoframeworktcs.dto.FuncionarioDTO;
import com.example.projetoframeworktcs.dto.FuncionarioResponseDTO;
import com.example.projetoframeworktcs.model.Funcionario;
import com.example.projetoframeworktcs.model.Setor;
import com.example.projetoframeworktcs.service.FuncionarioService;
import com.example.projetoframeworktcs.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FuncionarioController {

    private final SetorService setorService;
    private final FuncionarioService funcionarioService;

    public FuncionarioController(SetorService setorService, FuncionarioService funcionarioService) {
        this.setorService = setorService;
        this.funcionarioService = funcionarioService;
    }

//    @PostMapping("/adicionar")
//    public ResponseEntity<Funcionario> addFuncionario(@RequestBody FuncionarioDTO funcionario) {
//        Funcionario f = funcionarioService.adicionarFuncionario(funcionario);
//        return ResponseEntity.ok(f);
//    }

//    @GetMapping("/listar")
//    public ResponseEntity<List<Funcionario>> getFuncionarios() {
//        List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();
//        return ResponseEntity.ok(funcionarios);
//    }
//
//    @DeleteMapping("/remover")
//    public ResponseEntity<Void> deleteFuncionario(Long id) {
//        funcionarioService.removerFuncionario(id);
//        return ResponseEntity.noContent().build();
//    }

//    @PutMapping("/atualizar")
//    public ResponseEntity<Funcionario> updateFuncionario(Long id, AtualizarFuncionarioDTO dto) {
//        Funcionario f = funcionarioService.atualizarFuncionario(id, dto);
//        return ResponseEntity.ok(f);
//    }

    @GetMapping("/funcionarios")
    public String paginaInicialFuncionario(Model model, @PageableDefault(size = 6, sort = "id") Pageable pageable) {
        String qtdFuncionarios = funcionarioService.quantidadeFuncionarios() + " funcionário(s)";
        Page<FuncionarioResponseDTO> paginaDeFuncionarios = funcionarioService.listarFuncionarios(pageable);
        model.addAttribute("paginaDeFuncionarios", paginaDeFuncionarios);
        model.addAttribute("qtdFuncionarios", qtdFuncionarios);
        return "funcionario_inicial";
    }

    @GetMapping("/adicionar_funcionario")
    public String paginaAdicionarFuncionario(Model model) {
        model.addAttribute("funcionarioDTO", new CriarFuncionarioDTO("", "", "", 0, 0));
        List<Setor> setores = setorService.getSetores();
        model.addAttribute("setores", setores);
        return "adicionar_funcionario";
    }

    @PostMapping("/funcionarios/adicionar")
    public String registraFuncionario(CriarFuncionarioDTO dto, RedirectAttributes redirectAttributes) {
        funcionarioService.registrarFuncionario(dto);
        redirectAttributes.addFlashAttribute("sucesso", "Funcionário cadastrado com sucesso!");
        return "redirect:/funcionarios";
    }

    @DeleteMapping("/funcionarios/{id}")
    public String deletaFuncionario(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        funcionarioService.removerFuncionario(id);
        redirectAttributes.addFlashAttribute("sucesso", "Funcionário deletado com sucesso!");
        return "redirect:/funcionarios";
    }

}


