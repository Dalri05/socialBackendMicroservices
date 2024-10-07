package com.socialCloneMicroservices.AccountService.repository;

import com.socialCloneMicroservices.AccountService.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Integer> {
}
