package com.metropolitan.controllers;

import com.metropolitan.dtos.AutomobilDTO;
import com.metropolitan.dtos.UpdateAutomobilDTO;
import com.metropolitan.models.Automobil;
import com.metropolitan.services.AutomobilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Automobil> createAutomobil(@RequestBody AutomobilDTO automobilDTO) {
        Automobil automobil= automobilService.createAutomobil(automobilDTO);
        if(automobil != null) {
            return ResponseEntity.ok(automobil);
        }
        return ResponseEntity.badRequest().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    public ResponseEntity<Automobil> updateAutomobil(@RequestBody UpdateAutomobilDTO updateAutomobilDTO) {
        Automobil automobil = automobilService.updateAutomobil(updateAutomobilDTO);
        if(automobil != null) {
            return ResponseEntity.ok(automobil);
        }
        return ResponseEntity.badRequest().build();
    }

}
