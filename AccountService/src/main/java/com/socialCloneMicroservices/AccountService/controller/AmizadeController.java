package com.socialCloneMicroservices.AccountService.controller;

import com.socialCloneMicroservices.AccountService.Enums.ResponseEnum;
import com.socialCloneMicroservices.AccountService.model.AccountModel;
import com.socialCloneMicroservices.AccountService.service.AccountService;
import com.socialCloneMicroservices.AccountService.service.AmizadeService;
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

    @Autowired
    private AmizadeService amizadeService;

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

    @PutMapping("/conta/solicitacao/aceitar/{idSolicitante}/{idSolicitado}")
    public ResponseEntity<?> aceitarSolicitacao(@PathVariable Integer idSolicitante, @PathVariable Integer idSolicitado) {
        try{
            String response = amizadeService.aceitarSolicitacao(idSolicitante, idSolicitado);
            return ResponseEntity.ok(response);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/conta/solicitacao/recusar/{idSolicitante}/{idSolicitado}")
    public ResponseEntity<?> recusarSolicitacao(@PathVariable Integer idSolicitante, @PathVariable Integer idSolicitado) {
        try{
            String response = amizadeService.recusarSolicatacao(idSolicitante, idSolicitado);
            return ResponseEntity.ok(response);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
