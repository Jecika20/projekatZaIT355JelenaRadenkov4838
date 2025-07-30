package com.metropolitan.repositories;

import com.metropolitan.models.Korisnik;
import com.metropolitan.models.Rezervacija;
import com.metropolitan.models.Vozilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Integer> {
    Rezervacija findById(int id);
    List<Rezervacija> findByKorisnik(Korisnik korisnik);
    List<Rezervacija> findByKorisnikAndVozilo(Korisnik korisnik, Vozilo vozilo);
}
