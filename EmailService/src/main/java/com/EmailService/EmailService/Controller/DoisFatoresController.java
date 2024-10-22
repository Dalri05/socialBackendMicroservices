package com.EmailService.EmailService.Controller;

import com.EmailService.EmailService.Service.DoisFatoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
public class DoisFatoresController {
    @Autowired
    private DoisFatoresService doisFatoresService;
    
    @PostMapping("/send/2FA/{idConta}/{email}")
    public String send2FA(@PathVariable int idConta,@PathVariable String email){
        try {
            return doisFatoresService.enviarDoisFatores(idConta, email);
        } catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
