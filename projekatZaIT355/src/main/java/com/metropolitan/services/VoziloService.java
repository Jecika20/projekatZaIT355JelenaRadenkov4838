package com.metropolitan.services;

import com.metropolitan.enums.StatusVozila;
import com.metropolitan.models.Vozilo;
import com.metropolitan.repositories.VoziloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoziloService {

    @Autowired
    private VoziloRepository voziloRepository;

    public List<Vozilo> getAll() {
        return voziloRepository.findAll();
    }
    public Vozilo getById(int id) {
        return voziloRepository.findById(id);
    }

    public List<Vozilo> getVozilaBySalon(int salonId) {
        return voziloRepository.findBySalonId(salonId);
    }

    public Vozilo changeToRezervisano(int id) {
        Vozilo vozilo = voziloRepository.findById(id);
        if(vozilo == null){
            return null;
        }
        vozilo.setStatusVozila(StatusVozila.REZERVISAN);
        return voziloRepository.save(vozilo);

    }

    public Vozilo changeToDostupno(int id) {
        Vozilo vozilo = voziloRepository.findById(id);
        if(vozilo == null){
            return null;
        }
        vozilo.setStatusVozila(StatusVozila.DOSTUPAN);
        return voziloRepository.save(vozilo);
    }

    public Vozilo changeToPopravka(int id) {
        Vozilo vozilo = voziloRepository.findById(id);
        if(vozilo == null){
            return null;
        }
        vozilo.setStatusVozila(StatusVozila.POPRAVKA);
        return voziloRepository.save(vozilo);
    }
}
