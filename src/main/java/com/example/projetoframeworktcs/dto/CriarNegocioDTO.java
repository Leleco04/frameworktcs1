package com.example.projetoframeworktcs.dto;

import com.example.projetoframeworktcs.model.enums.Transportadora;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class CriarNegocioDTO {
    @NotNull
    private String status;

    @NotEmpty
    private List<Long> funcionarioIds;

    private LocalDateTime dataProgramada;

    @NotNull
    private Long produtoId;

    @NotNull
    private int quantidade;

    private Transportadora transportadora;

    public CriarNegocioDTO(){}

    private String tipo;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Long> getFuncionarioIds() {
        return funcionarioIds;
    }

    public void setFuncionarioIds(List<Long> funcionarioIds) {
        this.funcionarioIds = funcionarioIds;
    }

    public LocalDateTime getDataProgramada() {
        return dataProgramada;
    }

    public void setDataProgramada(LocalDateTime dataProgramada) {
        this.dataProgramada = dataProgramada;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }
}