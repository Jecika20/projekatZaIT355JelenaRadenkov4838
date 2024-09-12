package com.metropolitan.repositories;

import com.metropolitan.models.SlikeVozila;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlikeVozilaRepository extends JpaRepository<SlikeVozila, Integer> {

}
