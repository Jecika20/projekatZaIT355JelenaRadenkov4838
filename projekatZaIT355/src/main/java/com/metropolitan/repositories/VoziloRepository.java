package com.metropolitan.repositories;

import com.metropolitan.models.Vozilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoziloRepository extends JpaRepository<Vozilo, Integer> {
    Vozilo findById(int id);
}
