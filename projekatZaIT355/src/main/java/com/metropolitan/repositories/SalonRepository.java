package com.metropolitan.repositories;

import com.metropolitan.models.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalonRepository extends JpaRepository<Salon, Integer> {
    Salon findById(int id);
}
