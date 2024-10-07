package com.socialCloneMicroservices.AccountService.service;

import com.socialCloneMicroservices.AccountService.Enums.TipoContaEnum;
import com.socialCloneMicroservices.AccountService.model.AccountModel;
import com.socialCloneMicroservices.AccountService.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    private AccountModel conta;

    public void salvarConta(AccountModel conta){
        if (conta.getDataNascimento() == null) return;

        if (isContaMenorIdade(conta.getDataNascimento())) {
            conta.setTipoConta(TipoContaEnum.MENOR_IDADE);
        } else {
            conta.setTipoConta(TipoContaEnum.MAIOR_IDADE);
        }
        accountRepository.save(conta);
    }

    public boolean isContaMenorIdade(Date dataNascimento){
        int idade = Period.between(
                dataNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                LocalDate.now()
        ).getYears();

        if (idade < 18)
            return true;

        return false;
    }
}
