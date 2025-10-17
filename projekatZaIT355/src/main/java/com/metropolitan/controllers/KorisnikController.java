package com.metropolitan.controllers;

import com.metropolitan.dtos.KorisnikDTO;
import com.metropolitan.dtos.RadnikDTO;
import com.metropolitan.models.Korisnik;
import com.metropolitan.models.Radnik;
import com.metropolitan.services.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/zaposleniAdmini")
    public ResponseEntity<List<Korisnik>> zaposleniAdmini(){
        List<Korisnik> korisnici= korisnikService.zaposleniAdmini();
        if(korisnici!=null){
            return ResponseEntity.ok(korisnici);
        }
        return ResponseEntity.badRequest().build();

    }
    @GetMapping("/zaposleniRadnici")
    public ResponseEntity<List<Radnik>> zaposleniRadnici(){
        List<Radnik> radnici= korisnikService.zaposleniRadnici();
        if(radnici!=null){
            return ResponseEntity.ok(radnici);
        }
        return ResponseEntity.badRequest().build();

    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/updateRadnik/{id}")
    public ResponseEntity<Radnik> updateRadnik(@PathVariable int id,@RequestBody RadnikDTO radnikDTO){
        Radnik radnik= korisnikService.updateRadnik(id,radnikDTO);
        if(radnik!=null){
            return ResponseEntity.ok(radnik);
        }
        return ResponseEntity.badRequest().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/updateAdmin/{id}")
    public ResponseEntity<Korisnik> updateAdmin(@PathVariable int id,@RequestBody KorisnikDTO korisnikDTO){
       Korisnik korisnik= korisnikService.updateAdmin(id,korisnikDTO);
        if(korisnik!=null){
            return ResponseEntity.ok(korisnik);
        }
        return ResponseEntity.badRequest().build();
    }
}
