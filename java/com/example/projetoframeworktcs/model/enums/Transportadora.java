package com.example.projetoframeworktcs.model.enums;

import lombok.Getter;

@Getter
public enum Transportadora {

        LONDRINA("Londrina", 100.0),
        CAMBE("Cambe", 200.0),
        ROLANDIA("Rolandia", 300.0);

        private String cidadeTransportadora;
        private Double valorFreteFixo;

        Transportadora(String cidadeTransportadora, Double valorFreteFixo) {
            this.cidadeTransportadora = cidadeTransportadora;
            this.valorFreteFixo = valorFreteFixo;
        }

    }

