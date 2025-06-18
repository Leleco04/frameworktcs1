package com.example.projetoframeworktcs.dto;

public class AtualizarProdutoDTO {
    private long id;
    private String nome;
    private String descricao;
    private Double valorCompra;
    private Double valorVenda;
    private Integer qtdEstoque;
    private Long id_categoria;

    public AtualizarProdutoDTO(long id, String nome, String descricao, Double valorCompra, Double valorVenda, Integer qtdEstoque, Long id_categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.qtdEstoque = qtdEstoque;
        this.id_categoria = id_categoria;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public Long getId_categoria() { return id_categoria; }
    public void setId_categoria(Long id_categoria) { this.id_categoria = id_categoria; }
    public Integer getQtdEstoque() { return qtdEstoque; }
    public void setQtdEstoque(Integer qtdEstoque) { this.qtdEstoque = qtdEstoque; }
    public Double getValorVenda() { return valorVenda; }
    public void setValorVenda(Double valorVenda) { this.valorVenda = valorVenda; }
    public Double getValorCompra() { return valorCompra; }
    public void setValorCompra(Double valorCompra) { this.valorCompra = valorCompra; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
