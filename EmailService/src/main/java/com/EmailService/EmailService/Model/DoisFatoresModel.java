package com.EmailService.EmailService.Model;


import com.EmailService.EmailService.Enum.DoisFatoresEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "DoisFatores")
public class DoisFatoresModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int idContaDoisFatores;

    private String email;

    private String codigo;

    private LocalDateTime dataExpiracao;

    @Enumerated(EnumType.STRING)
    private DoisFatoresEnum situacao;
}
