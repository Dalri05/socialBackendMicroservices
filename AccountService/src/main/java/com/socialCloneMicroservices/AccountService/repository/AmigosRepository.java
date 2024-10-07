package com.socialCloneMicroservices.AccountService.repository;

import com.socialCloneMicroservices.AccountService.model.AmigoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmigosRepository extends JpaRepository<AmigoModel, Integer>{
}
