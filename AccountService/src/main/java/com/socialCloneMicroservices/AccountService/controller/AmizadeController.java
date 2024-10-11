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

    @PutMapping("/conta/solicitacao/enviar/{user}/{idcontaenviar}")
    public ResponseEntity enviarSolicitacao(@PathVariable String user,int idConta){
        ResponseEnum response = accountService.enviarSolicitacao(user, idConta);
        if (response.equals(ResponseEnum.SUCESS))
            return ResponseEntity.ok().build();

        return ResponseEntity.badRequest().build();
    }

}
