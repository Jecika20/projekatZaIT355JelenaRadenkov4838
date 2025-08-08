package com.metropolitan.repositories;

import com.metropolitan.models.Korisnik;
import com.metropolitan.models.Recenzija;
import com.metropolitan.models.Vozilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecenzijaRepository extends JpaRepository<Recenzija, Integer> {
    Recenzija findById(int id);
   List<Recenzija> findByAktivnaIsTrue();
   Recenzija findByKorisnikAndVozilo(Korisnik korisnik, Vozilo vozilo);

    List<Recenzija> findByAktivnaIsFalse();
}
