package com.socialCloneMicroservices.AccountService.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Embeddable
public class ContaBloqueadaModel {
    private static final long serialVersionUID = 1L;

    private String usuario;

    private Date dataBloqueio;

}
