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

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository, SetorRepository setorRepository, SalarioService salarioService) {
        this.funcionarioRepository = funcionarioRepository;
        this.setorRepository = setorRepository;
        this.salarioService = salarioService;
    }

    public void registrarFuncionario(CriarFuncionarioDTO dto) {
        if(dto.idade() < 18) {
            throw new RuntimeException("O funcionário deve ser maior de idade.");
        } else {
            Setor setorObj = setorRepository.findById(dto.idSetor())
                    .orElseThrow(() -> new RuntimeException("Setor não encontrado."));

            Funcionario funcionario = new Funcionario(dto.nome(), dto.sobrenome(), dto.genero(), dto.idade(), setorObj);

            funcionario.setSalario(salarioService.calcularSalarioCompleto(funcionario));

            funcionarioRepository.save(funcionario);

            setorObj.incrementarNumeroFuncionarios();
            setorRepository.save(setorObj);
        }
    }

    public List<FuncionarioResponseDTO> getFuncionariosAlmoxarifado(Long idSetor) {

        Setor setor = setorRepository.findById(idSetor)
                .orElseThrow(() -> new RuntimeException("Setor não encontrado."));

        List<Funcionario> funcionarios = funcionarioRepository.findBySetor(setor);

        return funcionarios.stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public long quantidadeFuncionarios() {
        return funcionarioRepository.count();
    }

    public Funcionario buscarFuncionarioPorId(Long id) {
        return funcionarioRepository.findById(id).orElseThrow( () -> new RuntimeException("Funcionário não encontrado."));
    }

    public AtualizarFuncionarioDTO atualizarFuncionarioPorId(Long id) {
        Funcionario f = buscarFuncionarioPorId(id);
        AtualizarFuncionarioDTO dto = new AtualizarFuncionarioDTO(f.getId(), f.getNome(), f.getSobrenome(), f.getIdade(), f.getGenero(), f.getSetor().getId());
        return dto;
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

    public List<FuncionarioResponseDTO> getFuncionarios() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();

        return funcionarios.stream()
                .map(funcionario -> new FuncionarioResponseDTO(
                        funcionario.getId(), funcionario.getNome(), funcionario.getSobrenome(), funcionario.getGenero(),
                        funcionario.getIdade(), funcionario.getSetor().getNome(), funcionario.getSalario().getSalarioLiquido()
                ))
                .collect(Collectors.toList());
    }

    public void removerFuncionario(Long id) {
        Funcionario funcionario = buscarFuncionarioPorId(id);
        funcionarioRepository.delete(funcionario);
    }

    public Funcionario atualizarFuncionario(Long id, AtualizarFuncionarioDTO dto) {
        Funcionario funcionario = buscarFuncionarioPorId(id);

        Setor setorObj = setorRepository.findById(dto.getIdSetor())
                .orElseThrow(() -> new RuntimeException("Setor não encontrado."));

        funcionario.setNome(dto.getNome());
        funcionario.setSobrenome(dto.getSobrenome());
        funcionario.setIdade(dto.getIdade());
        funcionario.setGenero(dto.getGenero());
        funcionario.setSetor(setorObj);

        return funcionarioRepository.save(funcionario);
    }

}
