package com.metropolitan.services;

import com.metropolitan.dtos.RezervacijaDTO;
import com.metropolitan.dtos.StatistikaRezervacijaVozilaDTO;
import com.metropolitan.enums.StatusRezervacije;
import com.metropolitan.enums.StatusVozila;
import com.metropolitan.models.Korisnik;
import com.metropolitan.models.Radnik;
import com.metropolitan.models.Rezervacija;
import com.metropolitan.models.Vozilo;
import com.metropolitan.repositories.KorisnikRepository;
import com.metropolitan.repositories.RadnikRepository;
import com.metropolitan.repositories.RezervacijaRepository;
import com.metropolitan.repositories.VoziloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RezervacijaService {


    @Autowired
    private RezervacijaRepository rezervacijaRepository;

    @Autowired
    private VoziloRepository voziloRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private RadnikRepository radnikRepository;
    public List<Rezervacija> getAll() {
        return rezervacijaRepository.findAll();
    }
    public Rezervacija getById(int id) {
        return rezervacijaRepository.findById(id);
    }

    public List<Rezervacija> getRezervacijeByKorisnik(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Korisnik korisnik = korisnikRepository.findByEmail(email);
        if(korisnik == null){
            return null;
        }
        return rezervacijaRepository.findByKorisnik(korisnik);
    }

    public Rezervacija createRezervacija(RezervacijaDTO rezervacijaDTO){
         String email = SecurityContextHolder.getContext().getAuthentication().getName();
         Korisnik korisnik = korisnikRepository.findByEmail(email);
         Vozilo vozilo = voziloRepository.findById(rezervacijaDTO.getVozilo_id());
         if(korisnik == null || vozilo == null){
             return null;
         }
         if(!vozilo.getStatusVozila().equals(StatusVozila.DOSTUPAN)){
             return null;
         }
         vozilo.setStatusVozila(StatusVozila.REZERVISAN);
         Rezervacija rezervacija = new Rezervacija();
         rezervacija.setVozilo(vozilo);
         rezervacija.setKorisnik(korisnik);
         rezervacija.setVremeOd(rezervacijaDTO.getVremeOd());
         rezervacija.setVremeDo(rezervacijaDTO.getVremeDo());
         rezervacija.setUkupnaCena(rezervacijaDTO.getUkupnaCena());
         rezervacija.setStatusRezervacije(StatusRezervacije.AKTIVNA);
         return rezervacijaRepository.save(rezervacija);
    }

    public Rezervacija statusToZavrseno(int id) {
        Rezervacija rezervacija = rezervacijaRepository.findById(id);
        if(rezervacija == null){
            return null;
        }
        rezervacija.setStatusRezervacije(StatusRezervacije.ZAVRSENA);
        rezervacija.getVozilo().setStatusVozila(StatusVozila.DOSTUPAN);
        return rezervacijaRepository.save(rezervacija);
    }

    public Rezervacija statusToOtkazano(int id) {
        Rezervacija rezervacija = rezervacijaRepository.findById(id);
        if(rezervacija == null){
            return null;
        }
        rezervacija.setStatusRezervacije(StatusRezervacije.OTKAZANA);
        rezervacija.getVozilo().setStatusVozila(StatusVozila.DOSTUPAN);
        return rezervacijaRepository.save(rezervacija);
    }


    public Rezervacija deleteRezervacija(int id) {
        Rezervacija rezervacija = rezervacijaRepository.findById(id);
        if(rezervacija == null){
            return null;
        }
        rezervacija.getVozilo().setStatusVozila(StatusVozila.DOSTUPAN);
        rezervacijaRepository.delete(rezervacija);
        return rezervacija;
    }
    public List<StatistikaRezervacijaVozilaDTO> getStatistikaPoVoziluIStatusu(){
       return rezervacijaRepository.brojRezervacijaPoStatusuIVozilu();
    }
    public List<Rezervacija> getRezervacijeByRadnikSalona(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Radnik radnik = radnikRepository.findByEmail(email);
        if(radnik == null){
            return null;
        }
        return rezervacijaRepository.findByVoziloSalon(radnik.getSalon());
    }
}
