package com.dev.controletiro.model.enums;

public enum TipoArma {

    REVOLVER("Revolver"),
    PISTOLA("Pistola"),
    CARABINA("Carabina"),
    FUZIL("Fuzil"),
    ESPINGARDA("Espingarda"),
    METRALHADORA("Metralhadora"),
    SUBMETRALHADORA("Submetralhadora");

    private String tipoArma;

    TipoArma(String tipoArma) {
        this.tipoArma = tipoArma;
    }

    public String getTipoArma() {
        return tipoArma;
    }
}
