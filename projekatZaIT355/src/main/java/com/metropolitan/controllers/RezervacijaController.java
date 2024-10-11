package com.metropolitan.controllers;

import com.metropolitan.dtos.RezervacijaDTO;
import com.metropolitan.models.Rezervacija;
import com.metropolitan.services.RezervacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rezervacija")
public class RezervacijaController {
      @Autowired
      private RezervacijaService rezervacijaService;

      @PreAuthorize("hasAnyAuthority('ADMIN','KLIJENT')")
      @PostMapping
      public ResponseEntity<Rezervacija> createRezervacija(@RequestBody RezervacijaDTO rezervacijaDTO){
        Rezervacija rezervacija = rezervacijaService.createRezervacija(rezervacijaDTO);
        if(rezervacija != null){
            return ResponseEntity.ok(rezervacija);
        }
        return ResponseEntity.badRequest().build();
      }

      @PreAuthorize("hasAuthority('ADMIN')")
      @GetMapping("/statusToZavrseno/{id}")
      public ResponseEntity<Rezervacija> changeStatusToZavrseno(@PathVariable int id){
          Rezervacija rezervacija= rezervacijaService.statusToZavrseno(id);
          if(rezervacija != null){
              return ResponseEntity.ok(rezervacija);
          }
          return ResponseEntity.notFound().build();
      }
      @PreAuthorize("hasAnyAuthority('ADMIN','KLIJENT')")
      @GetMapping("/statusToOtkazano/{id}")
      public ResponseEntity<Rezervacija> changeStatusToOtkazano(@PathVariable int id){
          Rezervacija rezervacija= rezervacijaService.statusToOtkazano(id);
          if(rezervacija != null){
              return ResponseEntity.ok(rezervacija);
          }
          return ResponseEntity.notFound().build();
      }

     @PreAuthorize("hasAuthority('ADMIN')")
     @DeleteMapping("/{id}")
      public ResponseEntity<Rezervacija> deleteRezervacija(@PathVariable int id){
          Rezervacija rezervacija = rezervacijaService.deleteRezervacija(id);
          if(rezervacija != null){
              return ResponseEntity.ok(rezervacija);
          }
         return ResponseEntity.notFound().build();
      }
}
