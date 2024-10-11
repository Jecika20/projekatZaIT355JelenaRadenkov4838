package com.metropolitan.services;

import com.metropolitan.dtos.SalonDTO;
import com.metropolitan.models.Salon;
import com.metropolitan.repositories.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalonService {

    @Autowired
    private SalonRepository salonRepository;

    public List<Salon> getAllSalons() {
        return salonRepository.findAll();
    }

    public Salon getSalonById(int id) {
        return salonRepository.findById(id);
    }

    public Salon createSalon(SalonDTO salonDTO) {
        Salon salon = new Salon();
        salon.setNaziv(salonDTO.getNaziv());
        salon.setAdresa(salonDTO.getAdresa());
        salon.setTelefon(salonDTO.getTelefon());
        salon.setEmail(salonDTO.getEmail());
        salon.setOpis(salonDTO.getOpis());
        salon.setGrad(salonDTO.getGrad());
        return salonRepository.save(salon);
    }

    public Salon deleteSalon(int id) {
        Salon salon = salonRepository.findById(id);
        if(salon == null){
            return null;
        }
        salonRepository.delete(salon);
        return salon;
    }
}
