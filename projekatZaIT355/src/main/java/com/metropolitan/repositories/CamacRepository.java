package com.metropolitan.repositories;

import com.metropolitan.models.Camac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CamacRepository extends JpaRepository<Camac, Integer> {
    Camac findCamacById(int id);
}
