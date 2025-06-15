package com.example.projetoframeworktcs.service;

import com.example.projetoframeworktcs.dto.AtualizarFuncionarioDTO;
import com.example.projetoframeworktcs.dto.FuncionarioDTO;
import com.example.projetoframeworktcs.model.Funcionario;
import com.example.projetoframeworktcs.model.Salario;
import com.example.projetoframeworktcs.model.Setor;
import com.example.projetoframeworktcs.repository.FuncionarioRepository;
import com.example.projetoframeworktcs.repository.SetorRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public Funcionario buscarFuncionarioPorId(Long id) {
        return funcionarioRepository.findById(id).orElseThrow( () -> new RuntimeException("Funcionário não encontrado."));
    }

    // private SalarioService salarioService;

    /* public Funcionario adicionarFuncionario(FuncionarioDTO funcionario) {
        Salario salario = salarioService.calcularSalario(funcionario.getVale(), funcionario.getPlanoSaude(), funcionario.getPlanoOdontologico(), funcionario.getBonusParticipacao(), funcionario.getTaxaAliquota(), funcionario.getSalarioBruto());
        Funcionario funcionarioSalvo = new Funcionario(funcionario.getNome(), funcionario.getSobrenome(), funcionario.getIdade(), funcionario.getGenero(), salario, funcionario.getId_setor());

        return funcionarioRepository.save(funcionarioSalvo);
    } */

    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public void removerFuncionario(Long id) {
        Funcionario funcionario = buscarFuncionarioPorId(id);
        funcionarioRepository.delete(funcionario);
    }

    /* public Funcionario atualizarFuncionario(Long id, AtualizarFuncionarioDTO dto) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado."));

        funcionario.setNome(dto.getNome());
        funcionario.setSobrenome(dto.getSobrenome());
        funcionario.setIdade(dto.getIdade());
        funcionario.setGenero(dto.getGenero());
        funcionario.setId_setor(dto.getId_setor());

        return funcionarioRepository.save(funcionario);
    } */
}