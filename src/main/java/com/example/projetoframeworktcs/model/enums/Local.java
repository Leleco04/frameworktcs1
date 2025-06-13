package com.example.projetoframeworktcs.model.enums;

import lombok.Getter;

@Getter
public enum Local {


        LONDRINA("Londrina", 1000.0),
        CAMBE("Cambe", 2000.0),
        ROLANDIA("Rolandia", 3000.0);

        private String cidadeTransportadora;
        private double valorFreteFixo;


        Local(String cidadeTransportadora, double valorFreteFixo) {
            this.cidadeTransportadora = cidadeTransportadora;
            this.valorFreteFixo = valorFreteFixo;
        }

    }

