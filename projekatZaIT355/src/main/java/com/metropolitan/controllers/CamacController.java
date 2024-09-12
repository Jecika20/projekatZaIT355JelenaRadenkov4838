package com.metropolitan.controllers;

import com.metropolitan.models.Camac;
import com.metropolitan.services.CamacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/camac")
public class CamacController {

    @Autowired
    private CamacService camacService;

    @GetMapping
    public ResponseEntity<List<Camac>> getAllCamac() {
        List<Camac> camci = camacService.getAllCamac();
        return ResponseEntity.ok(camci);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Camac> getCamacById(@PathVariable  int id) {
        Camac camac = camacService.getCamacById(id);
        if(camac != null) {
            return ResponseEntity.ok(camac);
        }
        return ResponseEntity.notFound().build();
    }

}
