package com.metropolitan;

import com.metropolitan.controllers.AutomobilController;
import com.metropolitan.dtos.AutomobilDTO;
import com.metropolitan.dtos.UpdateAutomobilDTO;
import com.metropolitan.enums.StatusVozila;
import com.metropolitan.enums.TipGoriva;
import com.metropolitan.enums.VrstaAutomobila;
import com.metropolitan.models.Automobil;
import com.metropolitan.models.Salon;
import com.metropolitan.services.AutomobilService;
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
public class AutomobilControllerTest {


    @Mock
    private AutomobilService automobilService;

    @InjectMocks
    private AutomobilController automobilController;

    private Salon salon;
    private Automobil automobil1;
    private Automobil automobil2;

    @BeforeEach
    public void setUp() {
        salon = new Salon();
        salon.setId(1);


        automobil1 = new Automobil();
        automobil1.setId(1);
        automobil1.setModel("Fiat");
        automobil1.setBrend("Punto");
        automobil1.setGodinaProizvodnje(2005);
        automobil1.setCenaPoDanu(100.0);
        automobil1.setStatusVozila(StatusVozila.DOSTUPAN);
        automobil1.setKilometraza(150000);
        automobil1.setOpis("Opis");
        automobil1.setBrojVrata(4);
        automobil1.setTipGoriva(TipGoriva.BENZIN);
        automobil1.setBrojSedista(5);
        automobil1.setVrstaAutomobila(VrstaAutomobila.DZIP);
        automobil1.setSalon(salon);

        automobil2 = new Automobil();
        automobil2.setId(2);
        automobil2.setModel("Opel");
        automobil2.setBrend("Astra");
        automobil2.setGodinaProizvodnje(2010);
        automobil2.setCenaPoDanu(120.0);
        automobil2.setStatusVozila(StatusVozila.DOSTUPAN);
        automobil2.setKilometraza(120000);
        automobil2.setOpis("Opis");
        automobil2.setBrojVrata(4);
        automobil2.setTipGoriva(TipGoriva.DIZEL);
        automobil2.setBrojSedista(5);
        automobil2.setVrstaAutomobila(VrstaAutomobila.LIMUZINA);
        automobil2.setSalon(salon);
    }
    @Test
    public void testGetAutomobilById() {
        when(automobilService.getAutomobilById(1)).thenReturn(automobil1);

        ResponseEntity<Automobil> response = automobilController.getAutomobilById(1);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Fiat", response.getBody().getModel());
    }
    @Test
    public void testGetAllAutomobili() {

        when(automobilService.getAllAutomobil()).thenReturn(Arrays.asList(automobil1, automobil2));


        ResponseEntity<List<Automobil>> response = automobilController.getAllAutomobili();


        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Fiat", response.getBody().get(0).getModel());
        assertEquals("Opel", response.getBody().get(1).getModel());
    }
    @Test
    public void testCreateAutomobil() {
        AutomobilDTO automobilDTO = new AutomobilDTO();
        automobilDTO.setModel("Volkswagen");
        automobilDTO.setBrend("Golf");
        automobilDTO.setGodinaProizvodnje(2020);
        automobilDTO.setCenaPoDanu(150.0);
        automobilDTO.setStatusVozila("DOSTUPAN");
        automobilDTO.setKilometraza(50000);
        automobilDTO.setOpis("Opis");
        automobilDTO.setBrojVrata(4);
        automobilDTO.setTipGoriva("BENZIN");
        automobilDTO.setBrojSedista(5);
        automobilDTO.setVrstaAutomobila("LIMUZINA");
        automobilDTO.setSalon_id(1);


        when(automobilService.createAutomobil(automobilDTO)).thenReturn(automobil1);


        ResponseEntity<Automobil> response = automobilController.createAutomobil(automobilDTO);


        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Fiat", response.getBody().getModel());
    }
    @Test
    public void testUpdateAutomobil() {
        UpdateAutomobilDTO updateAutomobilDTO = new UpdateAutomobilDTO();
        updateAutomobilDTO.setId(1);
        updateAutomobilDTO.setModel("Fiat");
        updateAutomobilDTO.setBrend("Punto");
        updateAutomobilDTO.setGodinaProizvodnje(2022);
        updateAutomobilDTO.setCenaPoDanu(250.0);
        updateAutomobilDTO.setStatusVozila("REZERVISAN");
        updateAutomobilDTO.setKilometraza(30000);
        updateAutomobilDTO.setOpis("Nov automobil");
        updateAutomobilDTO.setBrojVrata(4);
        updateAutomobilDTO.setTipGoriva("BENZIN");
        updateAutomobilDTO.setBrojSedista(5);
        updateAutomobilDTO.setVrstaAutomobila("DZIP");


        when(automobilService.updateAutomobil(updateAutomobilDTO)).thenReturn(automobil1);


        ResponseEntity<Automobil> response = automobilController.updateAutomobil(updateAutomobilDTO);


        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Fiat", response.getBody().getModel());
    }
    @Test
    public void testCreateAutomobilBadRequest() {
        AutomobilDTO automobilDTO = new AutomobilDTO();


        when(automobilService.createAutomobil(automobilDTO)).thenReturn(null);

        ResponseEntity<Automobil> response = automobilController.createAutomobil(automobilDTO);

        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    public void testUpdateAutomobilBadRequest() {
        UpdateAutomobilDTO updateAutomobilDTO = new UpdateAutomobilDTO();
        updateAutomobilDTO.setId(1);


        when(automobilService.updateAutomobil(updateAutomobilDTO)).thenReturn(null);

        ResponseEntity<Automobil> response = automobilController.updateAutomobil(updateAutomobilDTO);

        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    public void testGetAutomobilByIdNotFound() {

        when(automobilService.getAutomobilById(3)).thenReturn(null);


        ResponseEntity<Automobil> response = automobilController.getAutomobilById(3);


        assertEquals(404, response.getStatusCodeValue());
    }
}
