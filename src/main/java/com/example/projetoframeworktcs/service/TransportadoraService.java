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

    public void calcularFrete(String transportadoraEscolhida, double toneladas) {
        Local local = buscarcidadeTransportadora(transportadoraEscolhida);

        if (local != null) {
            double valorFinal;
            double valorExtra = 200;
            valorFinal = valorExtra * toneladas;
            System.out.println("O frete fixo para " + local.getCidadeTransportadora() + " é: R$ " + local.getValorFreteFixo());
            System.out.println("O valor final do frete ficou: " + (valorFinal + local.getValorFreteFixo()));
        } else {
            System.out.println("Transportadora não encontrada nesta cidade");
        }
    }

    public void exibirTransportadora(Transportadora transportadora){

        System.out.println("Possuimos: " + transportadora.getQtdParceiras() + " Transportadoras" );
        System.out.print("Cidades disponíveis: ");
        for(Local local: Local.values()){
            System.out.print(local.getCidadeTransportadora() + " ");
        }
    }

}

