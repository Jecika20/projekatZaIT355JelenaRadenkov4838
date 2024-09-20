package com.metropolitan.repositories;

import com.metropolitan.models.Vozilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoziloRepository extends JpaRepository<Vozilo, Integer> {
    Vozilo findById(int id);

    List<Vozilo> findBySalonId(int salonId);
}
