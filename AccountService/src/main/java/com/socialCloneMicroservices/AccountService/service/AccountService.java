package com.socialCloneMicroservices.AccountService.service;

import com.socialCloneMicroservices.AccountService.Enums.ResponseEnum;
import com.socialCloneMicroservices.AccountService.Enums.TipoContaEnum;
import com.socialCloneMicroservices.AccountService.model.AccountModel;
import com.socialCloneMicroservices.AccountService.model.ContaBloqueadaModel;
import com.socialCloneMicroservices.AccountService.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public String bloquearConta(int idConta, String userBloquear){
        try {
            Optional<AccountModel> optionalConta = accountRepository.findById(idConta);
            Optional<AccountModel> optionalContaBloquear = Optional.ofNullable(accountRepository.findByUsuario(userBloquear));

            if (!optionalConta.isPresent()) {
                return "Conta não encontrada!";
            }
            conta = optionalConta.get();
            AccountModel contaBloquear = optionalContaBloquear.get();

            if(isContaBloqueada(conta, userBloquear)){
                return "Conta ja bloqueada!";
            }

            if(idConta == contaBloquear.getId()){
                return "Não é possivel bloquear sua conta!";
            }

            List<ContaBloqueadaModel> listContasBloqueadas = conta.getContasBloqueadas();
            ContaBloqueadaModel novaContaBloqueada = new ContaBloqueadaModel();
            novaContaBloqueada.setUsuario(userBloquear);
            novaContaBloqueada.setDataBloqueio(new Date());
            listContasBloqueadas.add(novaContaBloqueada);

            accountRepository.save(conta);
            return "Conta bloqueada com sucesso!";
        } catch (Exception e) {
            return "Falha ao bloquear a conta";
        }
    }

    public boolean isContaBloqueada(AccountModel conta, String userBloqeuar){
        return conta.getContasBloqueadas().stream()
                .anyMatch(contaBloqueada -> contaBloqueada.getUsuario().equals(userBloqeuar));

    }

    public List<AccountModel> getTodasContas(){
        return accountRepository.findAll();
    }

    public ResponseEnum deletarConta(int idConta){
        try {
            List<AccountModel> listaContas = accountRepository.findAll();
            if(!listaContas.isEmpty() && listaContas.stream().anyMatch(conta -> conta.getId() == idConta)){
                accountRepository.deleteById(idConta);
                return ResponseEnum.SUCESS;
            }
            return ResponseEnum.NOT_FOUND;
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEnum.BAD;
        }
    }
}
