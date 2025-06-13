package com.example.projetoframeworktcs.service;

import com.example.projetoframeworktcs.model.Funcionario;
import com.example.projetoframeworktcs.model.Setor;
import com.example.projetoframeworktcs.repository.FuncionarioRepository;
import com.example.projetoframeworktcs.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final SetorRepository setorRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, SetorRepository setorRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.setorRepository = setorRepository;
    }

    public void registrarFuncionario(String nome, String sobrenome, String genero, int idade, long setor) {
        Setor setorObj = setorRepository.findById(setor)
                .orElseThrow(() -> new RuntimeException("Setor não encontrado."));

        Funcionario funcionario = new Funcionario(nome, sobrenome, genero, idade, setorObj);

        funcionarioRepository.save(funcionario);
    }

    public long quantidadeFuncionarios() {
        return funcionarioRepository.count();
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Funcionario buscarFuncionarioPorId(Long id) {
        return funcionarioRepository.findById(id).orElseThrow( () -> new RuntimeException("Funcionário não encontrado."));
    }

}
