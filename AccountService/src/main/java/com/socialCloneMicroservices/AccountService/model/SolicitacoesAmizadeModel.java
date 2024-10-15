package com.socialCloneMicroservices.AccountService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.socialCloneMicroservices.AccountService.Enums.StatusSolicitacaoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "solicitacoesAmizade")
public class SolicitacoesAmizadeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "solicitante_id", nullable = false)
    @JsonIgnore
    private AccountModel solicitante;

    @ManyToOne
    @JoinColumn(name = "solicitado_id", nullable = false)
    @JsonIgnore
    private AccountModel solicitado;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusSolicitacaoEnum status;

    @Column(name = "data_solicitacao", nullable = false)
    private Date dataSolicitacao;
}
