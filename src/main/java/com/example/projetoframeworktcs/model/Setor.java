package com.example.projetoframeworktcs.model;

import jakarta.persistence.*;

@Entity
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "quantidade_funcionarios")
    private Integer quantidadeFuncionarios;

    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public Integer getQuantidadeFuncionarios() {
        return quantidadeFuncionarios;
    }
}
