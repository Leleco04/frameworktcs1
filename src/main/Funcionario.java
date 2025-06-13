package model;

import enumeracao.Genero;
import exception.GeneroInvalidoException;
import exception.SetorInvalidoException;
import exception.QuantidadeLimiteFuncionariosException;
import exception.CodigoUnicoExistenteException;
import setor.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Funcionario {

    @Id
    private String codigoFuncionario;
    private String nome;
    private String sobrenome;
    private int idade;
    private Genero genero;
    private double salario;
    private Setor setor;
    private int qtdVendas;

    // Construtores, getters e setters

    public Funcionario(String nome, String sobrenome, String codigoFuncionario, int idade, int genero, Setor setor) throws GeneroInvalidoException, SetorInvalidoException, QuantidadeLimiteFuncionariosException, CodigoUnicoExistenteException {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.codigoFuncionario = codigoFuncionario;
        this.idade = idade;

        this.genero = definirGenero(genero);
        if (this.genero == null) {
            throw new exception.GeneroInvalidoException("Entrada inválida: gênero não encontrado.");
        }

        this.setor = setor;
        this.qtdVendas = 0;
    }

    public Genero definirGenero(int genero) {
        switch (genero) {
            case 1: return Genero.MASCULINO;
            case 2: return Genero.FEMININO;
            default: return null;
        }
    }

    public void incrementarVendas() {
        this.qtdVendas++;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", codigoFuncionario='" + codigoFuncionario + '\'' +
                ", idade=" + idade +
                ", genero=" + genero +
                ", salario=" + salario +
                ", setor=" + setor +
                ", qtdVendas=" + qtdVendas +
                '}';
    }
}
