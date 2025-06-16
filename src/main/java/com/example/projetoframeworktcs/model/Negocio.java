package com.example.projetoframeworktcs.model;

import com.example.projetoframeworktcs.model.enums.Status;
import com.example.projetoframeworktcs.model.enums.TipoNegocio;
import com.example.projetoframeworktcs.model.enums.Transportadora;
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
    private Transportadora transportadora;

    public Negocio(Double valorNegocio, Status status, ArrayList<String> funcionariosEnvolvidos, ArrayList<ItemNegocio> listaProdutos, LocalDateTime dataProgramada, TipoNegocio tipo, Transportadora transportadora) {
        this.valorNegocio = valorNegocio;
        this.status = status;
        this.funcionariosEnvolvidos = funcionariosEnvolvidos;
        this.listaProdutos = listaProdutos;
        this.dataProgramada = dataProgramada;
        this.tipo = tipo;
        this.transportadora = transportadora;
    }

    public LocalDateTime getDataNegocio() { return dataNegocio; }
    public Double getValorNegocio() { return valorNegocio; }
    public void setValorNegocio(Double valorNegocio) { this.valorNegocio = valorNegocio; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public ArrayList<String> getFuncionariosEnvolvidos() { return funcionariosEnvolvidos; }
    public void setFuncionariosEnvolvidos(ArrayList<String> funcionariosEnvolvidos) { this.funcionariosEnvolvidos = funcionariosEnvolvidos; }
    public ArrayList<ItemNegocio> getListaProdutos() { return listaProdutos; }
    public void setListaProdutos(ArrayList<ItemNegocio> listaProdutos) { this.listaProdutos = listaProdutos; }
    public LocalDateTime getDataProgramada() { return dataProgramada; }
    public String getDataProgramadaFormatada() { return formatter.format(dataProgramada); }
    public void setDataProgramada(LocalDateTime dataProgramada) { this.dataProgramada = dataProgramada; }
    public TipoNegocio getTipo() { return tipo; }
    public void setTipo(TipoNegocio tipo) { this.tipo = tipo; }
    public Transportadora getTransportadora() { return transportadora; }
    public void setTransportadora(Transportadora transportadora) { this.transportadora = transportadora; }
}
