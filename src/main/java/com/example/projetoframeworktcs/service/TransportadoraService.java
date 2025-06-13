package com.example.projetoframeworktcs.service;
import com.example.projetoframeworktcs.dto.AtualizarTransportadoraDTO;
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
                .orElseThrow(() -> new EntityNotFoundException("Transportadora não encontrado."));

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

    public Double calcularFrete(String transportadoraEscolhida, double toneladas) {
        Local local = buscarcidadeTransportadora(transportadoraEscolhida);

        if (local != null) {
            double valorFinal;
            double valorExtra = 200;
            valorFinal = valorExtra * toneladas;
            return valorFinal + local.getValorFreteFixo();
        }
        return null;
    }

    public String exibirTransportadora(Transportadora transportadora){

        StringBuilder sb = new StringBuilder();

        sb.append("Possuimos: " + transportadora.getQtdParceiras() + " Transportadoras" );
        sb.append("Cidades disponíveis: ");
        for(Local local: Local.values()){
            sb.append(local.getCidadeTransportadora() + " ");
        }
        return sb.toString();
    }

}

