package com.socialCloneMicroservices.AccountService.controller;

import com.socialCloneMicroservices.AccountService.model.AccountModel;
import com.socialCloneMicroservices.AccountService.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    AccountService service;

    @PostMapping("/conta/novaConta")
    public ResponseEntity criarConta(@RequestBody AccountModel conta){
        try {
            service.salvarConta(conta);
            return ResponseEntity.ok().body(conta);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
