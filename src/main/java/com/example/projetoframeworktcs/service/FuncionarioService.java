package service;

import model.Funcionario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    // Exemplo de um método que pode ser chamado para adicionar ou processar a lógica de vendas
    public Funcionario criarFuncionario(String nome, String sobrenome, String codigoFuncionario, int idade, int genero, Setor setor) {
        try {
            return new Funcionario(nome, sobrenome, codigoFuncionario, idade, genero, setor);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Exemplo de uma lista de funcionários que poderia ser retornada ou manipulada
    public List<Funcionario> listarFuncionarios() {
        // Este método normalmente faria uma busca no banco de dados, mas aqui é apenas um exemplo
        return List.of(new Funcionario("João", "Silva", "F001", 30, 1, Setor.VENDAS));
    }
}
