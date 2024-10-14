package com.socialCloneMicroservices.AccountService.controller;

import com.socialCloneMicroservices.AccountService.Enums.ResponseEnum;
import com.socialCloneMicroservices.AccountService.model.AccountModel;
import com.socialCloneMicroservices.AccountService.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoArgsConstructor
@AllArgsConstructor
public class AmizadeController {

    @Autowired
    AccountService accountService;

    @PutMapping("/conta/solicitacao/enviar/{user}/{idConta}")
    public ResponseEntity<?> enviarSolicitacao(@PathVariable String user, @PathVariable Long idConta) {
        if (idConta == null) {
            return ResponseEntity.badRequest().body("O ID da conta não pode ser nulo.");
        }

        ResponseEnum response = accountService.enviarSolicitacao(user, idConta);

        if (response == ResponseEnum.SUCESS) {
            return ResponseEntity.ok("Solicitação de amizade enviada com sucesso!");
        } else {
            return ResponseEntity.badRequest().body("Erro ao enviar solicitação de amizade.");
        }
    }
}
