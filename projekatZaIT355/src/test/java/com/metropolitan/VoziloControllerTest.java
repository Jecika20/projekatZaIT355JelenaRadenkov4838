package com.metropolitan;

import com.metropolitan.controllers.AutomobilController;
import com.metropolitan.controllers.VoziloController;
import com.metropolitan.dtos.AutomobilDTO;
import com.metropolitan.dtos.UpdateAutomobilDTO;
import com.metropolitan.enums.StatusVozila;
import com.metropolitan.enums.TipGoriva;
import com.metropolitan.enums.VrstaAutomobila;
import com.metropolitan.models.Automobil;
import com.metropolitan.models.Salon;
import com.metropolitan.models.Vozilo;
import com.metropolitan.services.AutomobilService;
import com.metropolitan.services.VoziloService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class VoziloControllerTest {

    @Mock
    private VoziloService voziloService;

    @InjectMocks
    private VoziloController voziloController;

    private Salon salon;
    private Vozilo vozilo1;
    private Vozilo vozilo2;
    private Vozilo vozilo3;

    @BeforeEach
    public void setUp() {
        salon = new Salon();
        salon.setId(1);

        vozilo1 = new Vozilo();
        vozilo1.setId(1);
        vozilo1.setModel("Audi");
        vozilo1.setBrend("A4");
        vozilo1.setGodinaProizvodnje(2020);
        vozilo1.setCenaPoDanu(120.0);
        vozilo1.setStatusVozila(StatusVozila.DOSTUPAN);
        vozilo1.setKilometraza(50000);
        vozilo1.setOpis("Suv");
        vozilo1.setSalon(salon);

        vozilo2 = new Vozilo();
        vozilo2.setId(2);
        vozilo2.setModel("BMW");
        vozilo2.setBrend("X5");
        vozilo2.setGodinaProizvodnje(2021);
        vozilo2.setCenaPoDanu(150.0);
        vozilo2.setStatusVozila(StatusVozila.REZERVISAN);
        vozilo2.setKilometraza(30000);
        vozilo2.setOpis("SUV");
        vozilo2.setSalon(salon);

        vozilo3 = new Vozilo();
        vozilo3.setId(2);
        vozilo3.setModel("BMW");
        vozilo3.setBrend("X5");
        vozilo3.setGodinaProizvodnje(2021);
        vozilo3.setCenaPoDanu(150.0);
        vozilo3.setStatusVozila(StatusVozila.POPRAVKA);
        vozilo3.setKilometraza(30000);
        vozilo3.setOpis("SUV");
        vozilo3.setSalon(salon);
    }

    @Test
    public void testGetAllVozila() {

        when(voziloService.getAll()).thenReturn(Arrays.asList(vozilo1, vozilo2));


        ResponseEntity<List<Vozilo>> response = voziloController.getAll();


        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Audi", response.getBody().get(0).getModel());
        assertEquals("BMW", response.getBody().get(1).getModel());
    }

    @Test
    public void testGetVoziloById() {

        when(voziloService.getById(1)).thenReturn(vozilo1);


        ResponseEntity<Vozilo> response = voziloController.getVoziloById(1);


        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getId());
        assertEquals("Audi", response.getBody().getModel());
    }

    @Test
    public void testGetVoziloByIdNotFound() {

        when(voziloService.getById(3)).thenReturn(null);


        ResponseEntity<Vozilo> response = voziloController.getVoziloById(3);


        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testGetVozilaBySalon() {

        when(voziloService.getVozilaBySalon(1)).thenReturn(Arrays.asList(vozilo1, vozilo2));


        ResponseEntity<List<Vozilo>> response = voziloController.getVozilaBySalon(1);


        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Audi", response.getBody().get(0).getModel());
        assertEquals("BMW", response.getBody().get(1).getModel());
    }

    @Test
    public void testChangeToRezervisano() {

        when(voziloService.changeToRezervisano(1)).thenReturn(vozilo2);


        ResponseEntity<Vozilo> response = voziloController.changeToRezervisano(1);


        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(StatusVozila.REZERVISAN, response.getBody().getStatusVozila());
    }

    @Test
    public void testChangeToDostupno() {

        when(voziloService.changeToDostupno(2)).thenReturn(vozilo1);


        ResponseEntity<Vozilo> response = voziloController.changeToDostupno(2);


        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(StatusVozila.DOSTUPAN, response.getBody().getStatusVozila());
    }

    @Test
    public void testChangeToPopravka() {

        when(voziloService.changeToPopravka(2)).thenReturn(vozilo3);


        ResponseEntity<Vozilo> response = voziloController.changeToPopravka(2);


        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(StatusVozila.POPRAVKA, response.getBody().getStatusVozila());
    }
    @Test
    public void testDeleteVozilo() {

        when(voziloService.deleteVozilo(1)).thenReturn(vozilo1);


        ResponseEntity<Vozilo> response = voziloController.deleteVozilo(1);


        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getId());
    }
    @Test
    public void testDeleteVoziloNotFound() {

        when(voziloService.deleteVozilo(3)).thenReturn(null);


        ResponseEntity<Vozilo> response = voziloController.deleteVozilo(3);


        assertEquals(404, response.getStatusCodeValue());
    }
}