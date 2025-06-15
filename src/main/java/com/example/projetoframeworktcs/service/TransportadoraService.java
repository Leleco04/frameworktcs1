package com.example.projetoframeworktcs.service;
import com.example.projetoframeworktcs.dto.AtualizarTransportadoraDTO;
import com.example.projetoframeworktcs.dto.TransportadoraDTO;
import com.example.projetoframeworktcs.model.Funcionario;
import com.example.projetoframeworktcs.model.Transportadora;
import com.example.projetoframeworktcs.model.enums.Local;
import com.example.projetoframeworktcs.repository.TransportadoraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TransportadoraService {

    @Autowired
    private TransportadoraRepository transportadoraRepository;

    public Transportadora buscarTransportadoraPorId(Long id) {
        return transportadoraRepository.findById(id).orElseThrow( () -> new RuntimeException("Transportadora não encontrada."));
    }

    public Transportadora adicionar(TransportadoraDTO dto) {
        Double frete = calcularFrete(dto.getTransportadoraEscolhida(), dto.getToneladas());
        Transportadora transportadora = new Transportadora(dto.getLocal(), dto.getNome(), frete, dto.getTransportadoraEscolhida(), dto.getToneladas());

        return transportadoraRepository.save(transportadora);
    }

    public List<Transportadora> listar() {
        return transportadoraRepository.findAll();
    }

    public void remover(Long id) {
        Transportadora transportadora = buscarTransportadoraPorId(id);
        transportadoraRepository.delete(transportadora);
    }

    public Transportadora atualizar(Long id, AtualizarTransportadoraDTO dto) {
        Transportadora transportadora = buscarTransportadoraPorId(id);

        transportadora.setNome(dto.getNome());
        transportadora.setValorFrete(dto.getValorFrete());

        return transportadoraRepository.save(transportadora);

    }

    public static Local buscarcidadeTransportadora(String cidadeTransportadora) {
        for (Local l : Local.values()) {
            if (l.getCidadeTransportadora().equalsIgnoreCase(cidadeTransportadora)) {
                return l;
            }
        }
        return null;
    }

    public Double calcularFrete(String transportadoraEscolhida, Double toneladas) {
        Local local = buscarcidadeTransportadora(transportadoraEscolhida);

        if (local != null) {
            Double valorFinal;
            Double valorExtra = 200.0;
            valorFinal = valorExtra * toneladas;
            return valorFinal + local.getValorFreteFixo();
        }
        return null;
    }

    public String exibirTransportadora(Transportadora transportadora){

        StringBuilder sb = new StringBuilder();

        sb.append("Possuímos: " + transportadora.getQtdParceiras() + " transportadoras" );
        sb.append("Cidades disponíveis: ");
        for(Local local: Local.values()){
            sb.append(local.getCidadeTransportadora() + " ");
        }
        return sb.toString();
    }

}

