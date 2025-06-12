package com.example.projetoframeworktcs.service;

import com.example.projetoframeworktcs.dto.AtualizarFuncionarioDTO;
import com.example.projetoframeworktcs.model.Funcionario;
import com.example.projetoframeworktcs.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario adicionar(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> listar() {
        return funcionarioRepository.findAll();
    }

    public void remover(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado."));
        funcionarioRepository.delete(funcionario);
    }

    public Funcionario atualizar(Long id, AtualizarFuncionarioDTO dto) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado."));

        funcionario.setNome(dto.getNome());
        funcionario.setSobrenome(dto.getSobrenome());
        funcionario.setIdade(dto.getIdade());
        funcionario.setId_genero(dto.getId_genero());
        funcionario.setId_setor(dto.getId_setor());

        return funcionarioRepository.save(funcionario);
    }
}