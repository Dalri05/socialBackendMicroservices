package com.EmailService.EmailService.Repository;

import com.EmailService.EmailService.Model.DoisFatoresModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoisFatoresRepository extends JpaRepository<DoisFatoresModel, Integer> {
}
