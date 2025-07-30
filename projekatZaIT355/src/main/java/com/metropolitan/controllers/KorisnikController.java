package com.metropolitan.controllers;

import com.metropolitan.dtos.RadnikDTO;
import com.metropolitan.models.Korisnik;
import com.metropolitan.models.Radnik;
import com.metropolitan.services.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/korisnik")
public class KorisnikController {
    @Autowired
    private KorisnikService korisnikService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Korisnik> deleteKorisnik(@PathVariable int id) {
        Korisnik korisnik = korisnikService.deleteKorisnik(id);
        if (korisnik != null) {
            return ResponseEntity.ok(korisnik);
        }
        return ResponseEntity.notFound().build();

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/kreiranjeRadnika")
    public ResponseEntity<Radnik> createRadnik(@RequestBody RadnikDTO radnikDTO){
        Radnik radnik= korisnikService.createRadnik(radnikDTO);
        if(radnik!=null){
            return ResponseEntity.ok(radnik);
        }
        return ResponseEntity.badRequest().build();
    }
}
