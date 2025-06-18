package com.example.projetoframeworktcs.controller;

import com.example.projetoframeworktcs.dto.AtualizarNegocioDTO;
import com.example.projetoframeworktcs.dto.CriarNegocioDTO;
import com.example.projetoframeworktcs.dto.FuncionarioResponseDTO;
import com.example.projetoframeworktcs.dto.ProdutoResponseDTO;
import com.example.projetoframeworktcs.model.Negocio;
import com.example.projetoframeworktcs.model.enums.Transportadora;
import com.example.projetoframeworktcs.service.FuncionarioService;
import com.example.projetoframeworktcs.service.NegocioService;
import com.example.projetoframeworktcs.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class NegocioController {

    @Autowired
    private NegocioService negocioService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private FuncionarioService funcionarioService;

    /*
    @PostMapping("/adicionar")
    public ResponseEntity<Negocio> addNegocio(@RequestBody Negocio negocio) {
        Negocio n = negocioService.adicionarNegocio(negocio);
        return ResponseEntity.ok(n);
    }

    @GetMapping("/listar-negocios")
    public ResponseEntity<List<Negocio>> getNegocios() {
        List<Negocio> negocios = negocioService.listarNegocios();
        return ResponseEntity.ok(negocios);
    }

    @GetMapping("/listar-vendas")
    public ResponseEntity<List<Negocio>> getVendas() {
        List<Negocio> vendas = negocioService.listarVendas();
        return ResponseEntity.ok(vendas);
    }

    @GetMapping("/listar-compras")
    public ResponseEntity<List<Negocio>> getCompras() {
        List<Negocio> compras = negocioService.listarCompras();
        return ResponseEntity.ok(compras);
    }

    @GetMapping("/listar-abertos")
    public ResponseEntity<List<Negocio>> getAbertos() {
        List<Negocio> abertos = negocioService.listarNegociosAbertos();
        return ResponseEntity.ok(abertos);
    }

    @DeleteMapping("/remover")
    public ResponseEntity<Void> deleteNegocio(Long id) {
        negocioService.removerNegocio(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Negocio> updateFuncionario(Long id, AtualizarNegocioDTO dto) {
        Negocio n = negocioService.atualizarNegocio(id, dto);
        return ResponseEntity.ok(n);
    }
    */

    @GetMapping("/registrar_compra")
    public String paginaRegistrarCompra(Model model) {
        List<ProdutoResponseDTO> produtos = produtoService.getProdutos();
        List<FuncionarioResponseDTO> funcionarios = funcionarioService.getFuncionariosAlmoxarifado(6L);
        model.addAttribute("transportadoras", Transportadora.values());
        model.addAttribute("produtos", produtos);
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("negocioDTO", new CriarNegocioDTO());
        return "registrar_compra";
    }

    @GetMapping("/registrar_venda")
    public String paginaRegistrarVenda(Model model) {
        List<ProdutoResponseDTO> produtos = produtoService.getProdutos();
        List<FuncionarioResponseDTO> funcionarios = funcionarioService.getFuncionarios();
        model.addAttribute("transportadoras", Transportadora.values());
        model.addAttribute("produtos", produtos);
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("negocioDTO", new CriarNegocioDTO());
        return "registrar_venda";
    }

    @PostMapping("/negocio/registrar")
    // Utiliza o parametro de ids
    public String registrarNegocio(CriarNegocioDTO dto, RedirectAttributes redirectAttributes, @RequestParam(name = "funcionariosIds") List<Long> idsDosFuncionarios) {
        dto.setFuncionarioIds(idsDosFuncionarios);
        try {
            negocioService.criar(dto);
            redirectAttributes.addFlashAttribute("sucesso", "Negócio registrado com sucesso!");
            return "redirect:/caixa";

        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
            return "redirect:/registrar_compra";
        }
    }
}
