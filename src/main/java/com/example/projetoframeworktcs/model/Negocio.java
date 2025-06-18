package com.example.projetoframeworktcs.model;

import com.example.projetoframeworktcs.model.enums.Status;
import com.example.projetoframeworktcs.model.enums.TipoNegocio;
import com.example.projetoframeworktcs.model.enums.Transportadora;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.A;
import org.hibernate.annotations.BatchSize;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Negocio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
    private final LocalDateTime dataNegocio = LocalDateTime.now();
    private Double valorNegocio;
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    private LocalDateTime dataProgramada;
    private String tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "transportadora")
    private Transportadora transportadora;

    private int quantidade;

    @ManyToMany
    @JoinTable(
        name = "negocio_funcionarios",
            joinColumns = @JoinColumn(name = "id_negocio"),
            inverseJoinColumns = @JoinColumn(name = "id_funcionario")
    )
    @BatchSize(size = 10)
    private Set<Funcionario> funcionarios = new HashSet<>();

    public Negocio() {}

    public Negocio(Double valorNegocio, String status, Set<Funcionario> funcionarios, Produto produto, LocalDateTime dataProgramada, String tipo, Transportadora transportadora, int quantidade) {
        this.valorNegocio = valorNegocio;
        this.status = status;
        this.funcionarios = funcionarios;
        this.produto = produto;
        this.dataProgramada = dataProgramada;
        this.tipo = tipo;
        this.transportadora = transportadora;
        this.quantidade = quantidade;
    }

    public Long getId() {return id;}
    public LocalDateTime getDataNegocio() { return dataNegocio; }
    public Double getValorNegocio() { return valorNegocio; }
    public void setValorNegocio(Double valorNegocio) { this.valorNegocio = valorNegocio; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }
    public LocalDateTime getDataProgramada() { return dataProgramada; }
    public String getDataProgramadaFormatada() { return formatter.format(dataProgramada); }
    public void setDataProgramada(LocalDateTime dataProgramada) { this.dataProgramada = dataProgramada; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Transportadora getTransportadora() { return transportadora; }
    public void setTransportadora(Transportadora transportadora) { this.transportadora = transportadora; }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Set<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
