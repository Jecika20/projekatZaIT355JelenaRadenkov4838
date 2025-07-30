package com.metropolitan.services;

import com.metropolitan.dtos.RecenzijaDTO;
import com.metropolitan.dtos.UpdateRecenzijaDTO;
import com.metropolitan.models.Korisnik;
import com.metropolitan.models.Recenzija;
import com.metropolitan.models.Rezervacija;
import com.metropolitan.models.Vozilo;
import com.metropolitan.repositories.KorisnikRepository;
import com.metropolitan.repositories.RecenzijaRepository;
import com.metropolitan.repositories.RezervacijaRepository;
import com.metropolitan.repositories.VoziloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecenzijaService {
    @Autowired
    private RecenzijaRepository recenzijaRepository;
   @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private VoziloRepository voziloRepository;

    @Autowired
    private RezervacijaRepository rezervacijaRepository;



    public List<Recenzija> getAllRecenzije(){
        return recenzijaRepository.findAll();
    }
    public Recenzija getRecenzijaById(int id){
        Recenzija recenzija= recenzijaRepository.findById(id);
        if(recenzija ==null){
            return null;
        }
        return recenzija;
    }

    public Recenzija createRecenzija(RecenzijaDTO recenzijaDTO){
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        Korisnik korisnik= korisnikRepository.findByEmail(email);
        Vozilo vozilo= voziloRepository.findById(recenzijaDTO.getVozilo_id());
        if(korisnik==null || vozilo==null){
            return null;
        }
        List<Rezervacija> rezervacije= rezervacijaRepository.findByKorisnikAndVozilo(korisnik,vozilo);
        if (rezervacije.isEmpty()) {
            return null;
        }
        if(recenzijaRepository.findByKorisnikAndVozilo(korisnik,vozilo)!=null){
            return null;
        }
        Recenzija recenzija= new Recenzija();
        recenzija.setAktivna(recenzijaDTO.isAktivna());
        recenzija.setKomentar(recenzijaDTO.getKomentar());
        recenzija.setVremeKreiranja(LocalDateTime.now());
        recenzija.setOcena(recenzijaDTO.getOcena());
        recenzija.setKorisnik(korisnik);
        recenzija.setVozilo(vozilo);
        return recenzijaRepository.save(recenzija);
    }
    public Recenzija updateRecenzija(int id, UpdateRecenzijaDTO updaterecenzijaDTO ){
        Recenzija recenzija = recenzijaRepository.findById(id);
        if(recenzija==null){
            return null;
        }

        recenzija.setAktivna(updaterecenzijaDTO.isAktivna());
        recenzija.setKomentar(updaterecenzijaDTO.getKomentar());
        recenzija.setOcena(updaterecenzijaDTO.getOcena());
       return recenzijaRepository.save(recenzija);
    }
    public Recenzija deleteRecenzija(int id){
        Recenzija recenzija= recenzijaRepository.findById(id);
        if(recenzija==null){
            return null;
        }
        recenzijaRepository.delete(recenzija);
        return recenzija;
    }

}
