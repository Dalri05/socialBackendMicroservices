package com.socialCloneMicroservices.AccountService.service;

import com.socialCloneMicroservices.AccountService.Enums.StatusSolicitacaoEnum;
import com.socialCloneMicroservices.AccountService.model.AccountModel;
import com.socialCloneMicroservices.AccountService.model.AmigoModel;
import com.socialCloneMicroservices.AccountService.model.SolicitacoesAmizadeModel;
import com.socialCloneMicroservices.AccountService.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmizadeService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    public String aceitarSolicitacao (int idSContaSolicitante, int idContaSolicitada){
        try {
            AccountModel contaSolicitante = accountRepository.findById(idSContaSolicitante).get();
            AccountModel contaSolicitada= accountRepository.findById(idContaSolicitada).get();
            List<AmigoModel> amigosContaSolicitante = contaSolicitante.getAmigos();
            List<AmigoModel> amigosContaSolicitada = contaSolicitada.getAmigos();


            if (!contaSolicitante.getSolicitacoesEnviadas().contains(idContaSolicitada)){
                return "Você não consegue aceitar uma solicitação não enviada";
            }

            for(SolicitacoesAmizadeModel accSolicitadaRecebidas : contaSolicitada.getSolicitacoesRecebidas()){
                if (accSolicitadaRecebidas.getStatus() == StatusSolicitacaoEnum.PENDENTE && accSolicitadaRecebidas.getSolicitante().getId() == idSContaSolicitante){
                    contaSolicitada.getSolicitacoesRecebidas().removeIf(solicitacao -> solicitacao.getSolicitante().getId() == idSContaSolicitante);
                    contaSolicitante.getSolicitacoesEnviadas().removeIf(solicitacao -> solicitacao.getSolicitado().getId() == idContaSolicitada);

                    AmigoModel novoAmigoContaSolicitada = new AmigoModel();
                    novoAmigoContaSolicitada.setId(contaSolicitante.getId());
                    novoAmigoContaSolicitada.setNome(contaSolicitante.getNome());
                    novoAmigoContaSolicitada.setConta(contaSolicitante);

                    AmigoModel novoAmigoContaSolicitante = new AmigoModel();
                    novoAmigoContaSolicitante.setId(contaSolicitada.getId());
                    novoAmigoContaSolicitante.setNome(contaSolicitada.getNome());
                    novoAmigoContaSolicitante.setConta(contaSolicitada);

                    amigosContaSolicitada.add(novoAmigoContaSolicitada);
                    amigosContaSolicitante.add(novoAmigoContaSolicitante);

                    if (!amigosContaSolicitada.contains(novoAmigoContaSolicitante)) {
                        amigosContaSolicitada.add(novoAmigoContaSolicitante);
                    }
                    if (!amigosContaSolicitante.contains(novoAmigoContaSolicitada)) {
                        amigosContaSolicitante.add(novoAmigoContaSolicitada);
                    }

                    accountRepository.save(contaSolicitada);
                    accountRepository.save(contaSolicitante);
                }
            }

            return "Conta Adicionada com sucesso";
        }
        catch (Exception e){
            e.printStackTrace();
            return "Erro ao adicionar uma conta";
        }
    }

    public String recusarSolicatacao (int idContaSolicitante, int idContaSolicitada){
        try {
            AccountModel contaSolicitante = accountRepository.findById(idContaSolicitante).get();
            AccountModel contaSolicitada = accountRepository.findById(idContaSolicitada).get();

            if (!contaSolicitante.getSolicitacoesEnviadas().stream()
                    .anyMatch(solicitacao -> solicitacao.getSolicitado().getId() == idContaSolicitada)) {
                return "Você não consegue recusar uma solicitação não enviada.";
            }

            boolean removida = contaSolicitada.getSolicitacoesRecebidas().removeIf(
                    solicitacao -> solicitacao.getStatus() == StatusSolicitacaoEnum.PENDENTE
                            && solicitacao.getSolicitante().getId() == idContaSolicitante
            );
            if (removida) {
                contaSolicitante.getSolicitacoesEnviadas().removeIf(
                        solicitacao -> solicitacao.getSolicitado().getId() == idContaSolicitada
                );

                accountRepository.save(contaSolicitada);
                accountRepository.save(contaSolicitante);

                return "Solicitação recusada com sucesso.";
            }

            return "Solicitação não encontrada ou já foi processada.";
        } catch (Exception e){
            e.printStackTrace();
            return "Erro ao recusar solicitação";
        }
    }
}
