package com.metropolitan.controllers;

import com.metropolitan.models.Automobil;
import com.metropolitan.services.AutomobilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/automobil")
public class AutomobilController {
    @Autowired
    private AutomobilService automobilService;

    @GetMapping
    public ResponseEntity<List<Automobil>> getAllAutomobili() {
        List<Automobil> automobili = automobilService.getAllAutomobil();
        return ResponseEntity.ok(automobili);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Automobil> getAutomobilById(@PathVariable int id) {
        Automobil automobil = automobilService.getAutomobilById(id);
        if(automobil != null) {
            return ResponseEntity.ok(automobil);
        }
        return ResponseEntity.notFound().build();
    }
}
