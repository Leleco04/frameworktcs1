package com.example.projetoframeworktcs.dto;

import com.example.projetoframeworktcs.model.ItemNegocio;
import com.example.projetoframeworktcs.model.enums.Status;
import com.example.projetoframeworktcs.model.enums.TipoNegocio;
import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
public class AtualizarNegocioDTO {
    private Double valorNegocio;
    private Status status;

    @ElementCollection
    private ArrayList<String> funcionariosEnvolvidos;

    @ElementCollection
    private ArrayList<ItemNegocio> listaProdutos;

    private LocalDateTime dataProgramada;
    private TipoNegocio tipo;
}
