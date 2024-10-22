package com.EmailService.EmailService.Enum;

public enum DoisFatoresEnum {
    VERIFICADA(0),
    PENDENTE(1),
    EXPIRADA(2);

    private final int situacaoDoisFatores;

    DoisFatoresEnum(int situacaoDoisFatores) {
        this.situacaoDoisFatores = situacaoDoisFatores;
    }

    public int getSituacaoDoisFatores() {
        return situacaoDoisFatores;
    }
}
