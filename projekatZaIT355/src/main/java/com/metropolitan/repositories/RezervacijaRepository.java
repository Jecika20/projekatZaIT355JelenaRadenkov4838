package com.metropolitan.repositories;

import com.metropolitan.models.Rezervacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Integer> {
    Rezervacija findById(int id);
}
