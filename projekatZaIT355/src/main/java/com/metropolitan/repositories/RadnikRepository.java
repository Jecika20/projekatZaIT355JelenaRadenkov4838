package com.metropolitan.repositories;

import com.metropolitan.models.Radnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RadnikRepository extends JpaRepository<Radnik,Integer> {
    Radnik findByEmail(String email);
}
