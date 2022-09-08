package com.dev.controletiro.model.enums;

public enum CalibreArma {

    MM380(".380"),
    MM22(".22"),
    MM25(".25"),
    MM32(".32"),
    MM40(".40"),
    MM9("9mm"),
    MM44(".44"),
    MM45(".45"),
    MM357(".357");

    private final String calibre;

    CalibreArma(String calibre) {
        this.calibre = calibre;
    }

    public String getCalibre() {
        return calibre;
    }
}
