package com.example.projetoframeworktcs.service;

import com.example.projetoframeworktcs.dto.AtualizarFuncionarioDTO;
import com.example.projetoframeworktcs.dto.CriarFuncionarioDTO;
import com.example.projetoframeworktcs.dto.FuncionarioDTO;
import com.example.projetoframeworktcs.dto.FuncionarioResponseDTO;
import com.example.projetoframeworktcs.model.Funcionario;
import com.example.projetoframeworktcs.model.Salario;
import com.example.projetoframeworktcs.model.Setor;
import com.example.projetoframeworktcs.repository.FuncionarioRepository;
import com.example.projetoframeworktcs.repository.SetorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public void registrarFuncionario(CriarFuncionarioDTO dto) {
        Setor setorObj = setorRepository.findById(dto.idSetor())
                .orElseThrow(() -> new RuntimeException("Setor não encontrado."));

        Funcionario funcionario = new Funcionario(dto.nome(), dto.sobrenome(), dto.genero(), dto.idade(), setorObj);

        funcionarioRepository.save(funcionario);
    }

    public long quantidadeFuncionarios() {
        return funcionarioRepository.count();
    }

    public Funcionario buscarFuncionarioPorId(Long id) {
        return funcionarioRepository.findById(id).orElseThrow( () -> new RuntimeException("Funcionário não encontrado."));
    }

    // private SalarioService.java salarioService;

    /* public Funcionario adicionarFuncionario(FuncionarioDTO funcionario) {
        Salario salario = salarioService.calcularSalario(funcionario.getVale(), funcionario.getPlanoSaude(), funcionario.getPlanoOdontologico(), funcionario.getBonusParticipacao(), funcionario.getTaxaAliquota(), funcionario.getSalarioBruto());
        Funcionario funcionarioSalvo = new Funcionario(funcionario.getNome(), funcionario.getSobrenome(), funcionario.getIdade(), funcionario.getGenero(), salario, funcionario.getId_setor());

        return funcionarioRepository.save(funcionarioSalvo);
    } */

    public Page<FuncionarioResponseDTO> listarFuncionarios(Pageable pageable) {
        Page<Funcionario> paginaDeFuncionarios = funcionarioRepository.findAll(pageable);

        return paginaDeFuncionarios.map(this::converterParaDTO);
    }

    private FuncionarioResponseDTO converterParaDTO(Funcionario funcionario) {
        return new FuncionarioResponseDTO(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getSobrenome(),
                funcionario.getGenero(),
                funcionario.getIdade(),
                funcionario.getSetor().getNome()
        );
    }

    public void removerFuncionario(Long id) {
        Funcionario funcionario = buscarFuncionarioPorId(id);
        funcionarioRepository.delete(funcionario);
    }


    // TERMINAR ***
    /* public Funcionario atualizarFuncionario(Long id, AtualizarFuncionarioDTO dto) {
        Funcionario funcionario = buscarFuncionarioPorId(id);

        funcionario.setNome(dto.getNome());
        funcionario.setSobrenome(dto.getSobrenome());
        funcionario.setIdade(dto.getIdade());
        funcionario.setGenero(dto.getGenero());
        funcionario.se(dto.getIdSetor());

        return funcionarioRepository.save(funcionario);
    } */
}
