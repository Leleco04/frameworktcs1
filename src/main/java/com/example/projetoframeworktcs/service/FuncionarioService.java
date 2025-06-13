package service;

import model.Funcionario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    public Funcionario criarFuncionario(String nome, String sobrenome, String codigoFuncionario, int idade, int genero, Setor setor) {
        try {
            return new Funcionario(nome, sobrenome, codigoFuncionario, idade, genero, setor);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Funcionario> listarFuncionarios() {
        return List.of(new Funcionario("João", "Silva", "F001", 30, 1, Setor.VENDAS));
    }
}
