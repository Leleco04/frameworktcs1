package com.example.projetoframeworktcs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Funcionario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private int idade;
    private int id_genero;
    private double salario;
    private int qtdVendas;
    private int id_setor;

    public Funcionario(String nome, String sobrenome, int idade, int id_genero, double salario, int qtdVendas, int id_setor) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.id_genero = id_genero;
        this.salario = salario;
        this.qtdVendas = qtdVendas;
        this.id_setor = id_setor;
    }

    public void incrementarVendas(){
        this.qtdVendas++;
    }

}
