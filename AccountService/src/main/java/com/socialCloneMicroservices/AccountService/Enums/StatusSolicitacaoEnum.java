package com.socialCloneMicroservices.AccountService.Enums;

public enum StatusSolicitacaoEnum {
    PENDENTE(99),
    ACEITA(1),
    RECUSADA(0);

    private final int statusSolicitacao;

    StatusSolicitacaoEnum(int statusSolicitacao) {
        this.statusSolicitacao = statusSolicitacao;
    }

    public int getStatusSolicitacao() {
        return statusSolicitacao;
    }
}
