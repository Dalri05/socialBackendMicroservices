package com.socialCloneMicroservices.AccountService.repository;

import com.socialCloneMicroservices.AccountService.Enums.StatusSolicitacaoEnum;
import com.socialCloneMicroservices.AccountService.model.AccountModel;
import com.socialCloneMicroservices.AccountService.model.SolicitacoesAmizadeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitacaoAmizadeRepository extends JpaRepository<SolicitacoesAmizadeModel, Long > {

    List<SolicitacoesAmizadeModel> findBySolicitanteAndSolicitadoAndStatus(AccountModel solicitante, AccountModel solicitado, StatusSolicitacaoEnum status);

}
