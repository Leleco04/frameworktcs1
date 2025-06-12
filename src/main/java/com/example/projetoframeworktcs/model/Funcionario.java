package com.example.projetoframeworktcs.model;

import com.example.projetoframeworktcs.enumeracao.Genero;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Funcionario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private Genero genero;
    private int idade;
    private double salario;
    private int qtdVendas;
}
