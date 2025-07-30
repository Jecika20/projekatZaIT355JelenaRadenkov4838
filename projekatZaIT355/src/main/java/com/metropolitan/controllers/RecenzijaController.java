package com.metropolitan.controllers;

import com.metropolitan.dtos.RecenzijaDTO;
import com.metropolitan.dtos.UpdateRecenzijaDTO;
import com.metropolitan.models.Recenzija;
import com.metropolitan.services.RecenzijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/recenzija")
public class RecenzijaController {
    @Autowired
    private RecenzijaService recenzijaService;

    @GetMapping()
    public ResponseEntity<List<Recenzija>> getAllRecenzija(){
        List<Recenzija> recenzija= recenzijaService.getAllRecenzije();
        return ResponseEntity.ok(recenzija);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Recenzija> getRecenzijaById(@PathVariable ("id")int id){
        Recenzija recenzija = recenzijaService.getRecenzijaById(id);
        if(recenzija==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(recenzija);
    }
    @PostMapping()
    public ResponseEntity<Recenzija> createRecenzija(@RequestBody RecenzijaDTO recenzijaDTO){
        System.out.println("Metoda radi");
        Recenzija recenzija = recenzijaService.createRecenzija(recenzijaDTO);
        if(recenzija==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(recenzija);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Recenzija> updateRecenzija(@PathVariable ("id")int id,@RequestBody UpdateRecenzijaDTO updaterecenzijaDTO){
        Recenzija recenzija= recenzijaService.updateRecenzija(id,updaterecenzijaDTO);
        if(recenzija==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(recenzija);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Recenzija> deleteRecenzija(@PathVariable("id")int id){
        Recenzija recenzija= recenzijaService.deleteRecenzija(id);
        if(recenzija==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(recenzija);
    }

}
