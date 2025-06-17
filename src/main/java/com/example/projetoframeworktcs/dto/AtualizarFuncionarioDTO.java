package com.example.projetoframeworktcs.dto;

import com.example.projetoframeworktcs.model.Setor;
import com.example.projetoframeworktcs.model.enums.Genero;
import lombok.Getter;
import lombok.Setter;

public class AtualizarFuncionarioDTO {
    private String nome;
    private String sobrenome;
    private Integer idade;
    private String genero;
    private long idSetor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public long getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(long idSetor) {
        this.idSetor = idSetor;
    }
}
