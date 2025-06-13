package com.example.projetoframeworktcs.model;

import com.example.projetoframeworktcs.model.enums.Genero;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Embedded
    private Salario salario;
    private Integer id_setor;

    public Funcionario(String nome, String sobrenome, Integer idade, Genero genero, Salario salario, Integer id_setor) {
        this.nome = nome;
        this.id_setor = id_setor;
        this.sobrenome = sobrenome;
        this.genero = genero;
        this.idade = idade;
        this.salario = salario;
    }

}
