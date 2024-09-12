package com.metropolitan.services;

import com.metropolitan.models.Camac;
import com.metropolitan.repositories.CamacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CamacService {

    @Autowired
    private CamacRepository camacRepository;

    public List<Camac> getAllCamac(){
        return camacRepository.findAll();
    }
    public Camac getCamacById(int id){
        return camacRepository.findCamacById(id);
    }

}


