package com.example.projetoframeworktcs.model;

import com.example.projetoframeworktcs.model.enums.Status;
import com.example.projetoframeworktcs.model.enums.TipoNegocio;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Negocio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
    private final LocalDateTime dataNegocio = LocalDateTime.now();
    private Double valorNegocio;
    private Status status;

    @ElementCollection
    private ArrayList<String> funcionariosEnvolvidos;

    @ElementCollection
    private ArrayList<ItemNegocio> listaProdutos;

    private LocalDateTime dataProgramada;
    private TipoNegocio tipo;

    public Negocio(Double valorNegocio, Status status, ArrayList<String> funcionariosEnvolvidos, ArrayList<ItemNegocio> listaProdutos, LocalDateTime dataProgramada, TipoNegocio tipo) {
        this.valorNegocio = valorNegocio;
        this.status = status;
        this.funcionariosEnvolvidos = funcionariosEnvolvidos;
        this.listaProdutos = listaProdutos;
        this.dataProgramada = dataProgramada;
        this.tipo = tipo;
    }

    public String getDataProgramadaFormatada() {
        return formatter.format(dataProgramada);
    }
}
