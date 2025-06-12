package com.example.projetoframeworktcs.controller;

import com.example.projetoframeworktcs.dto.AtualizarFuncionarioDTO;
import com.example.projetoframeworktcs.dto.AtualizarTransportadoraDTO;
import com.example.projetoframeworktcs.model.Funcionario;
import com.example.projetoframeworktcs.model.Transportadora;
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

    @PostMapping
    public ResponseEntity<Transportadora> addTransportadora(@RequestBody Transportadora transportadora) {
        Transportadora t = transportadoraService.adicionar(transportadora);
        return ResponseEntity.ok(t);
    }

    @GetMapping
    public ResponseEntity<List<Transportadora>> getAllTransportadoras() {
        List<Transportadora> transportadoras = transportadoraService.listar();
        return ResponseEntity.ok(transportadoras);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTransportadora(Long id) {
        transportadoraService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Transportadora> updateTransportadora(Long id, AtualizarTransportadoraDTO dto) {
        Transportadora t = transportadoraService.atualizar(id, dto);
        return ResponseEntity.ok(t);
    }

}
