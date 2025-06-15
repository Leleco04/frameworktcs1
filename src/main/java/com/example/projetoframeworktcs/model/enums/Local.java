package com.example.projetoframeworktcs.model.enums;

import lombok.Getter;

@Getter
public enum Local {

        LONDRINA("Londrina", 1000.0),
        CAMBE("Cambe", 2000.0),
        ROLANDIA("Rolandia", 3000.0);

        private String cidadeTransportadora;
        private Double valorFreteFixo;


        Local(String cidadeTransportadora, Double valorFreteFixo) {
            this.cidadeTransportadora = cidadeTransportadora;
            this.valorFreteFixo = valorFreteFixo;
        }

    }

