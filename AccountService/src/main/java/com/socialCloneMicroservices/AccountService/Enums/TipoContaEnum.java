package com.socialCloneMicroservices.AccountService.Enums;

public enum TipoContaEnum {

    MAIOR_IDADE(0),
    MENOR_IDADE(1);

    private final int tipoConta;

    TipoContaEnum(int tipoConta) {
        this.tipoConta = tipoConta;
    }

    public int getTipoConta() {
        return tipoConta;
    }
}
