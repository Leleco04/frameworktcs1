package com.example.projetoframeworktcs.service;

import com.example.projetoframeworktcs.model.Caixa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaixaService {

    @Autowired
    private Caixa caixa;

    public void adicionarValor(Double valor) {
        caixa.setValor(caixa.getValor() + valor);
    }

    public void removerValor(Double valor) {
        caixa.setValor(caixa.getValor() - valor);
    }

}
