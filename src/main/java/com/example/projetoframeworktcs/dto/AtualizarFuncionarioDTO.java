package com.example.projetoframeworktcs.dto;

public class AtualizarFuncionarioDTO {
    private long id;
    private String nome;
    private String sobrenome;
    private Integer idade;
    private String genero;
    private Long idSetor;

    public AtualizarFuncionarioDTO(long id, String nome, String sobrenome, Integer idade, String genero, Long idSetor) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.genero = genero;
        this.idSetor = idSetor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(Long idSetor) {
        this.idSetor = idSetor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
