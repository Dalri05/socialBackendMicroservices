package com.EmailService.EmailService.Service;

import com.EmailService.EmailService.Model.DoisFatoresModel;
import com.EmailService.EmailService.Repository.DoisFatoresRepository;
import com.EmailService.EmailService.Enum.DoisFatoresEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class DoisFatoresService {
    @Autowired
    private DoisFatoresRepository doisFatoresRepository;
    @Autowired
    private JavaMailSender mailSender;

    public String enviarDoisFatores(int idContaEnviarDoisFatores,String emailConta){
        try {
            String codigo = gerarCodigoDoisFatores();
            LocalDateTime tempoExpiracao = definirTempoExpiracao();

            DoisFatoresModel doisFatores = new DoisFatoresModel();
            doisFatores.setEmail(emailConta);
            doisFatores.setCodigo(codigo);
            doisFatores.setDataExpiracao(tempoExpiracao);
            doisFatores.setIdContaDoisFatores(idContaEnviarDoisFatores);
            doisFatores.setSituacao(DoisFatoresEnum.PENDENTE);
            doisFatoresRepository.save(doisFatores);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailConta);
            message.setSubject("Código de Autenticação de Dois Fatores");
            message.setText("Seu código de verificação é: " + codigo);

            mailSender.send(message);

            return "Dois Fatores enviado com sucesso!";
        } catch (Exception e){
            e.printStackTrace();
            return "Erro ao enviar dois fatores";
        }
    }

    public static LocalDateTime definirTempoExpiracao(){
        return LocalDateTime.now().plusMinutes(5);
    }

    public static String gerarCodigoDoisFatores(){
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

}
