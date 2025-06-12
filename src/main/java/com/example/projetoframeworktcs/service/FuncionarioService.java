package com.example.projetoframeworktcs.service;

import com.example.projetoframeworktcs.model.Funcionario;
import com.example.projetoframeworktcs.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public void save(Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
    }

    public long quantidadeFuncionarios() {
        return funcionarioRepository.count();
    }

    public Funcionario buscarFuncionarioPorId(Long id) {
        return funcionarioRepository.findById(id).orElseThrow( () -> new RuntimeException("Funcionário não encontrado."));
    }

}
