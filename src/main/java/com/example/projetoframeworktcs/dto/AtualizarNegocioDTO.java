package com.example.projetoframeworktcs.dto;

import com.example.projetoframeworktcs.model.ItemNegocio;
import com.example.projetoframeworktcs.model.enums.Status;
import com.example.projetoframeworktcs.model.enums.TipoNegocio;
import com.example.projetoframeworktcs.model.enums.Transportadora;
import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AtualizarNegocioDTO {
    private Double valorNegocio;
    private Status status;

    @ElementCollection
    private ArrayList<String> funcionariosEnvolvidos;

    @ElementCollection
    private ArrayList<ItemNegocio> listaProdutos;

    private LocalDateTime dataProgramada;
    private TipoNegocio tipo;
    private Transportadora transportadora;

    public Double getValorNegocio() {
        return valorNegocio;
    }

    public void setValorNegocio(Double valorNegocio) {
        this.valorNegocio = valorNegocio;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ArrayList<String> getFuncionariosEnvolvidos() {
        return funcionariosEnvolvidos;
    }

    public void setFuncionariosEnvolvidos(ArrayList<String> funcionariosEnvolvidos) {
        this.funcionariosEnvolvidos = funcionariosEnvolvidos;
    }

    public ArrayList<ItemNegocio> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(ArrayList<ItemNegocio> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public LocalDateTime getDataProgramada() {
        return dataProgramada;
    }

    public void setDataProgramada(LocalDateTime dataProgramada) {
        this.dataProgramada = dataProgramada;
    }

    public TipoNegocio getTipo() {
        return tipo;
    }

    public void setTipo(TipoNegocio tipo) {
        this.tipo = tipo;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }
}
