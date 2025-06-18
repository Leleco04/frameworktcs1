package com.example.projetoframeworktcs.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "quantidade_funcionarios")
    private Integer quantidadeFuncionarios;

    private Double salarioBase;

    public Double getSalarioBase() {
        return salarioBase;
    }

    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public Integer getQuantidadeFuncionarios() {
        return quantidadeFuncionarios;
    }

    public void incrementarNumeroFuncionarios() {
        this.quantidadeFuncionarios++;
    }
}
