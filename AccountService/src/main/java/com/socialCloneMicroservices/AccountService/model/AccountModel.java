package com.socialCloneMicroservices.AccountService.model;

import com.socialCloneMicroservices.AccountService.Enums.TipoContaEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "conta")
public class AccountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String usuario;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Date dataNascimento;

    @Column(nullable = false, unique = true)
    private String cpf;

    private String telefone;

    private Date dataCadastro = new Date();

    @Enumerated(EnumType.STRING)
    private TipoContaEnum tipoConta;

    @ManyToMany
    @JoinTable(
            name = "amigos",
            joinColumns = @JoinColumn(name = "conta_id"),
            inverseJoinColumns = @JoinColumn(name = "amigo_id")
    )    private List<AmigoModel> amigos;

    @ElementCollection
    @CollectionTable(name = "contasBloqueadas", joinColumns = @JoinColumn(name = "conta_id"))
    private List<ContaBloqueadaModel> contasBloqueadas;

}

