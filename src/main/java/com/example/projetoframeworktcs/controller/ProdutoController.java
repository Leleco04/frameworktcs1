package com.example.projetoframeworktcs.controller;

import com.example.projetoframeworktcs.dto.AtualizarFuncionarioDTO;
import com.example.projetoframeworktcs.dto.AtualizarProdutoDTO;
import com.example.projetoframeworktcs.dto.CriarProdutoDTO;
import com.example.projetoframeworktcs.dto.ProdutoResponseDTO;
import com.example.projetoframeworktcs.model.Categoria;
import com.example.projetoframeworktcs.model.Produto;
import com.example.projetoframeworktcs.model.Setor;
import com.example.projetoframeworktcs.service.CategoriaService;
import com.example.projetoframeworktcs.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
public class ProdutoController {

    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;

    public ProdutoController(ProdutoService produtoService, CategoriaService categoriaService) {
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/produto_inicial")
    public String paginaInicialProdutos(Model model, @PageableDefault(size = 6, sort = "id") Pageable pageable) {
        Page<ProdutoResponseDTO> produtos = produtoService.listarProdutos(pageable);
        String quantidade = produtoService.quantidadeProdutos() + " produto(s)";
        model.addAttribute("quantidade", quantidade);
        model.addAttribute("produtos", produtos);
        return "produto_inicial";
    }

    @GetMapping("/adicionar_produto")
    public String paginaAdicionarProduto(Model model) {
        model.addAttribute("produtoDTO", new CriarProdutoDTO("","",0.0,0.0,0,0));
        List<Categoria> categorias = categoriaService.listarCategorias();
        model.addAttribute("categorias", categorias);
        return "adicionar_produto";
    }

    @PostMapping("/produtos/adicionar")
    public String registraProduto(CriarProdutoDTO dto, RedirectAttributes redirectAttributes) {
        produtoService.registrarProduto(dto);
        redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");
        return "redirect:/produto_inicial";
    }

    @GetMapping("/{id}/atualizar_produto")
    public String paginaAtualizarProduto(@PathVariable Long id, Model model) {
        AtualizarProdutoDTO dto = produtoService.atualizarProdutoPorId(id);
        model.addAttribute("atualizarProdutoDTO", dto);
        List<Categoria> categorias = categoriaService.listarCategorias();
        model.addAttribute("categorias", categorias);
        return "atualizar_produto";
    }

    @PutMapping("/produtos/atualizar/{id}")
    public String atualizaProduto(@PathVariable Long id, AtualizarProdutoDTO dto, RedirectAttributes redirectAttributes) {
        produtoService.atualizarProduto(id, dto);
        redirectAttributes.addFlashAttribute("sucesso", "Produto atualizado com sucesso!");
        return "redirect:/produto_inicial";
    }

    @DeleteMapping("/produtos/{id}")
    public String deletaProduto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        produtoService.removerProduto(id);
        redirectAttributes.addFlashAttribute("sucesso", "Produto deletado com sucesso!");
        return "redirect:/produto_inicial";
    }
}