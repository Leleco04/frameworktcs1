package com.example.projetoframeworktcs.service;
import com.example.projetoframeworktcs.dto.AtualizarTransportadoraDTO;
import com.example.projetoframeworktcs.dto.TransportadoraDTO;
import com.example.projetoframeworktcs.model.enums.Transportadora;
import com.example.projetoframeworktcs.repository.TransportadoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TransportadoraService {

    @Autowired
    private TransportadoraRepository transportadoraRepository;

    public com.example.projetoframeworktcs.model.Transportadora buscarTransportadoraPorId(Long id) {
        return transportadoraRepository.findById(id).orElseThrow( () -> new RuntimeException("Transportadora não encontrada."));
    }

    public com.example.projetoframeworktcs.model.Transportadora adicionar(TransportadoraDTO dto) {
        Double frete = calcularFrete(dto.getTransportadoraEscolhida(), dto.getToneladas());
        com.example.projetoframeworktcs.model.Transportadora transportadora = new com.example.projetoframeworktcs.model.Transportadora(dto.getLocal(), dto.getNome(), frete, dto.getTransportadoraEscolhida(), dto.getToneladas());

        return transportadoraRepository.save(transportadora);
    }

    public List<com.example.projetoframeworktcs.model.Transportadora> listar() {
        return transportadoraRepository.findAll();
    }

    public void remover(Long id) {
        com.example.projetoframeworktcs.model.Transportadora transportadora = buscarTransportadoraPorId(id);
        transportadoraRepository.delete(transportadora);
    }

    public com.example.projetoframeworktcs.model.Transportadora atualizar(Long id, AtualizarTransportadoraDTO dto) {
        com.example.projetoframeworktcs.model.Transportadora transportadora = buscarTransportadoraPorId(id);

        transportadora.setNome(dto.getNome());
        transportadora.setValorFrete(dto.getValorFrete());

        return transportadoraRepository.save(transportadora);

    }

    public static Transportadora buscarcidadeTransportadora(String cidadeTransportadora) {
        for (Transportadora l : Transportadora.values()) {
            if (l.getCidadeTransportadora().equalsIgnoreCase(cidadeTransportadora)) {
                return l;
            }
        }
        return null;
    }

    public Double calcularFrete(String transportadoraEscolhida, Double toneladas) {
        Transportadora local = buscarcidadeTransportadora(transportadoraEscolhida);

        if (local != null) {
            Double valorFinal;
            Double valorExtra = 200.0;
            valorFinal = valorExtra * toneladas;
            return valorFinal + local.getValorFreteFixo();
        }
        return null;
    }

    public String exibirTransportadora(com.example.projetoframeworktcs.model.Transportadora transportadora){

        StringBuilder sb = new StringBuilder();

        sb.append("Possuímos: " + transportadora.getQtdParceiras() + " transportadoras" );
        sb.append("Cidades disponíveis: ");
        for(Transportadora local: Transportadora.values()){
            sb.append(local.getCidadeTransportadora() + " ");
        }
        return sb.toString();
    }

}

