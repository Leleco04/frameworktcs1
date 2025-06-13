package com.example.projetoframeworktcs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
public class Funcionario implements Serializable {
    private static final long serialVersionUID = 1L;

    public Funcionario() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    private String genero;

    private int idade;

    private double salario;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getGenero() {
        return genero;
    }

    public int getIdade() {
        return idade;
    }

    public double getSalario() {
        return salario;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    @ManyToOne
    @JoinColumn(name = "id_setor")
    private Setor setor;

    public Funcionario(String nome, String sobrenome, String genero, int idade, Setor setor) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.genero = genero;
        this.idade = idade;
        this.setor = setor;
    }
}