package com.example.projetoframeworktcs.dto;

import com.example.projetoframeworktcs.model.enums.Status;
import com.example.projetoframeworktcs.model.enums.TipoNegocio;
import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class AtualizarNegocioDTO {
    private Status status;

    @ElementCollection
    private List<String> funcionariosEnvolvidos;

    private Integer id_produto;
    private LocalDateTime dataProgramada;
    private TipoNegocio tipo;
}
