package com.metropolitan.repositories;

import com.metropolitan.models.Automobil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomobilRepository extends JpaRepository<Automobil, Integer> {
    Automobil findAutomobilById(int id);
}
