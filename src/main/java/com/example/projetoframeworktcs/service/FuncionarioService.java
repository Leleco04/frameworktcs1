package com.example.projetoframeworktcs.service;

import com.example.projetoframeworktcs.dto.AtualizarFuncionarioDTO;
import com.example.projetoframeworktcs.dto.FuncionarioDTO;
import com.example.projetoframeworktcs.model.Funcionario;
import com.example.projetoframeworktcs.model.Salario;
import com.example.projetoframeworktcs.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    private SalarioService salarioService;

    public Funcionario adicionar(FuncionarioDTO funcionario) {
        Salario salario = salarioService.calcularSalario(funcionario.getVale(), funcionario.getPlanoSaude(), funcionario.getPlanoOdontologico(), funcionario.getBonusParticipacao(), funcionario.getTaxaAliquota(), funcionario.getSalarioBruto());
        Funcionario funcionarioSalvo = new Funcionario(funcionario.getNome(), funcionario.getSobrenome(), funcionario.getIdade(), funcionario.getGenero(), salario, funcionario.getId_setor());

        return funcionarioRepository.save(funcionarioSalvo);
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
        funcionario.setGenero(dto.getGenero());
        funcionario.setId_setor(dto.getId_setor());

        return funcionarioRepository.save(funcionario);
    }
}
