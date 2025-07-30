package com.metropolitan;

import com.metropolitan.controllers.VoziloController;
import com.metropolitan.enums.StatusVozila;
import com.metropolitan.models.Vozilo;
import com.metropolitan.repositories.VoziloRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VoziloControllerIntegrationTest {
    @Autowired
    private VoziloController voziloController;

    @Autowired
    private  VoziloRepository voziloRepository;
    private Vozilo vozilo;

    @BeforeAll
    public  void setUp() {

        vozilo = new Vozilo();
        vozilo.setModel("Audi");
        vozilo.setBrend("A4");
        vozilo.setGodinaProizvodnje(2020);
        vozilo.setCenaPoDanu(120.0);
        vozilo.setStatusVozila(StatusVozila.DOSTUPAN);
        vozilo.setKilometraza(50000);
        vozilo.setOpis("Suv");
        voziloRepository.save(vozilo);
    }
    @Test
    @Transactional
    public void testGetVoziloByIdIntegration() {

        ResponseEntity<Vozilo> response = voziloController.getVoziloById(vozilo.getId());


        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(vozilo.getId(), response.getBody().getId());
        assertEquals("Audi", response.getBody().getModel());
    }
    @Test
    @Transactional
    public void testGetVoziloByIdNotFoundIntegration() {

        ResponseEntity<Vozilo> response = voziloController.getVoziloById(999);


        assertEquals(404, response.getStatusCodeValue());
    }
    @Test
    @Transactional
    public void testGetAllVozilaIntegration() {


        ResponseEntity<List<Vozilo>> response = voziloController.getAll();


        assertEquals(200, response.getStatusCodeValue());


        assertNotNull(response.getBody());

    }

}
