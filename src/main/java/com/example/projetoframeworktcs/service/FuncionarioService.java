package com.example.projetoframeworktcs.service;

import com.example.projetoframeworktcs.dto.AtualizarFuncionarioDTO;
import com.example.projetoframeworktcs.dto.CriarFuncionarioDTO;
import com.example.projetoframeworktcs.dto.FuncionarioResponseDTO;
import com.example.projetoframeworktcs.model.Funcionario;
import com.example.projetoframeworktcs.model.Salario;
import com.example.projetoframeworktcs.model.Setor;
import com.example.projetoframeworktcs.repository.FuncionarioRepository;
import com.example.projetoframeworktcs.repository.SetorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final SetorRepository setorRepository;
    private final SalarioService salarioService;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, SetorRepository setorRepository, SalarioService salarioService) {
        this.funcionarioRepository = funcionarioRepository;
        this.setorRepository = setorRepository;
        this.salarioService = salarioService;
    }

    public void registrarFuncionario(CriarFuncionarioDTO dto) {
        Setor setorObj = setorRepository.findById(dto.idSetor())
                .orElseThrow(() -> new RuntimeException("Setor não encontrado."));

        Funcionario funcionario = new Funcionario(dto.nome(), dto.sobrenome(), dto.genero(), dto.idade(), setorObj);
        salarioService.calcularSalarioCompleto(funcionario, dto.salarioBruto());

        funcionarioRepository.save(funcionario);
    }

    public long quantidadeFuncionarios() {
        return funcionarioRepository.count();
    }

    public Funcionario buscarFuncionarioPorId(Long id) {
        return funcionarioRepository.findById(id).orElseThrow( () -> new RuntimeException("Funcionário não encontrado."));
    }

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

    public Funcionario atualizarFuncionario(Long id, AtualizarFuncionarioDTO dto) {
        Funcionario funcionario = buscarFuncionarioPorId(id);

        Setor setorObj = setorRepository.findById(dto.idSetor())
                .orElseThrow(() -> new RuntimeException("Setor não encontrado."));

        funcionario.setNome(dto.nome());
        funcionario.setSobrenome(dto.sobrenome());
        funcionario.setIdade(dto.idade());
        funcionario.setGenero(dto.genero());
        funcionario.setSetor(setorObj);
        salarioService.calcularSalarioCompleto(funcionario, dto.salarioBruto());

        return funcionarioRepository.save(funcionario);
    }

}
