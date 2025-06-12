package com.example.projetoframeworktcs.service;
import com.example.projetoframeworktcs.dto.AtualizarFuncionarioDTO;
import com.example.projetoframeworktcs.dto.AtualizarTransportadoraDTO;
import com.example.projetoframeworktcs.model.Funcionario;
import com.example.projetoframeworktcs.model.Transportadora;
import com.example.projetoframeworktcs.repository.TransportadoraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TransportadoraService {

    @Autowired
    private TransportadoraRepository transportadoraRepository;

    public Transportadora adicionar(Transportadora transportadora) {
        return transportadoraRepository.save(transportadora);
    }

    public List<Transportadora> listar() {
        return transportadoraRepository.findAll();
    }

    public void remover(Long id) {
        Transportadora transportadora = transportadoraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transportadora não encontrado."));
        transportadoraRepository.delete(transportadora);
    }

    public Transportadora atualizar(Long id, AtualizarTransportadoraDTO dto) {
        Transportadora transportadora = transportadoraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado."));

        transportadora.setNome(dto.getNome());
        transportadora.setValorFrete(dto.getValorFrete());

        return transportadoraRepository.save(transportadora);

    }
}

