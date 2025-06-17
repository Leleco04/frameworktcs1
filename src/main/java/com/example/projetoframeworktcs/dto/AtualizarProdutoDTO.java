package com.example.projetoframeworktcs.dto;

public class AtualizarProdutoDTO {
    private String nome;
    private String descricao;
    private Double valorCompra;
    private Double valorVenda;
    private Integer qtdEstoque;
    private Long id_categoria;

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
