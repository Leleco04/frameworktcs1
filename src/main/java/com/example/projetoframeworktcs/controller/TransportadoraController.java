package com.example.projetoframeworktcs.controller;

import com.example.projetoframeworktcs.dto.AtualizarTransportadoraDTO;
import com.example.projetoframeworktcs.dto.TransportadoraDTO;
import com.example.projetoframeworktcs.model.Transportadora;
import com.example.projetoframeworktcs.model.enums.Local;
import com.example.projetoframeworktcs.service.TransportadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/transportadora")
public class TransportadoraController {

    @Autowired
    private TransportadoraService transportadoraService;

    @PostMapping("/adicionar")
    public ResponseEntity<Transportadora> addTransportadora(@RequestBody TransportadoraDTO dto) {
        Transportadora t = transportadoraService.adicionar(dto);
        return ResponseEntity.ok(t);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Transportadora>> getAllTransportadoras() {
        List<Transportadora> transportadoras = transportadoraService.listar();
        return ResponseEntity.ok(transportadoras);
    }

    @DeleteMapping("/remover")
    public ResponseEntity<Void> deletarTransportadora(Long id) {
        transportadoraService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Transportadora> updateTransportadora(Long id, AtualizarTransportadoraDTO dto) {
        Transportadora t = transportadoraService.atualizar(id, dto);
        return ResponseEntity.ok(t);
    }

    @GetMapping("/buscarCidade")
    public ResponseEntity<Local> buscarcidadeTransportadora(String cidadeTransportadora) {
        Local l = TransportadoraService.buscarcidadeTransportadora(cidadeTransportadora);
        return ResponseEntity.ok(l);
    }

    @GetMapping("/exibir")
    public ResponseEntity<String> exibirTransportadora(Transportadora transportadora) {
        String t = transportadoraService.exibirTransportadora(transportadora);
        return ResponseEntity.ok(t);
    }

}
