package com.example.projetoframeworktcs.controller;

import com.example.projetoframeworktcs.dto.AtualizarNegocioDTO;
import com.example.projetoframeworktcs.model.Negocio;
import com.example.projetoframeworktcs.service.NegocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/negocio")
public class NegocioController {

    @Autowired
    private NegocioService negocioService;

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
}
