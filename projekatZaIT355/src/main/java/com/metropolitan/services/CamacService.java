package com.metropolitan.services;

import com.metropolitan.dtos.CamacDTO;
import com.metropolitan.enums.StatusVozila;
import com.metropolitan.enums.TipCamca;
import com.metropolitan.enums.TipVozila;
import com.metropolitan.models.Camac;
import com.metropolitan.models.Salon;
import com.metropolitan.repositories.CamacRepository;
import com.metropolitan.repositories.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CamacService {

    @Autowired
    private CamacRepository camacRepository;

    @Autowired
    private SalonRepository salonRepository;

    public List<Camac> getAllCamac(){
        return camacRepository.findAll();
    }
    public Camac getCamacById(int id){
        return camacRepository.findCamacById(id);
    }

    public Camac createCamac(CamacDTO camacDTO) {
        Salon salon = salonRepository.findById(camacDTO.getSalon_id());
        if(salon == null){
            return null;
        }

        Camac camac = new Camac();
        camac.setModel(camacDTO.getModel());
        camac.setBrend(camacDTO.getBrend());
        camac.setGodinaProizvodnje(camacDTO.getGodinaProizvodnje());
        camac.setTipVozila(TipVozila.CAMAC);
        camac.setCenaPoDanu(camacDTO.getCenaPoDanu());
        camac.setStatusVozila(StatusVozila.valueOf(camacDTO.getStatusVozila()));
        camac.setKilometraza(camacDTO.getKilometraza());
        camac.setOpis(camacDTO.getOpis());
        camac.setDuzina(camacDTO.getDuzina());
        camac.setTipCamca(TipCamca.valueOf(camacDTO.getTipCamca()));
        camac.setSalon(salon);
        return camacRepository.save(camac);
    }

}


