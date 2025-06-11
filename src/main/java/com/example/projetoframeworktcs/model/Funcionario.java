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
    private Integer idade;
    private Integer id_genero;
    private Double salario;
    private Integer id_setor;

    public Funcionario(String nome, Integer id_setor, String sobrenome, Integer id_genero, Integer idade, Double salario) {
        this.nome = nome;
        this.id_setor = id_setor;
        this.sobrenome = sobrenome;
        this.id_genero = id_genero;
        this.idade = idade;
        this.salario = salario;
    }

}
