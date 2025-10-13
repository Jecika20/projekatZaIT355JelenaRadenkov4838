package com.metropolitan.repositories;

import com.metropolitan.enums.Uloga;
import com.metropolitan.models.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
    Korisnik findById(int id);
    Korisnik findByEmail(String email);
   List<Korisnik> findByUloga(Uloga uloga);
}
