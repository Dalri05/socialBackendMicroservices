package com.socialCloneMicroservices.AccountService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "amigos")
public class AmigoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Date dataNascimento;

    @ManyToOne
    @JoinColumn(name = "conta_id", nullable = false)
    private AccountModel conta;

}
