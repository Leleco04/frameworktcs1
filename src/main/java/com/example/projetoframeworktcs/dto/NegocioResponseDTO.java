package com.example.projetoframeworktcs.dto;

import com.example.projetoframeworktcs.model.Produto;
import com.example.projetoframeworktcs.model.enums.Transportadora;

import java.time.LocalDateTime;
import java.util.List;

public record NegocioResponseDTO (
        long id,
        String status,
        LocalDateTime dataProgramada,
        LocalDateTime dataNegocio,
        double valorNegocio,
        String tipo,
        Transportadora transportadora,
        int quantidade,
        String produto,
        List<FuncionarioResponseDTO> funcionarios
) { }