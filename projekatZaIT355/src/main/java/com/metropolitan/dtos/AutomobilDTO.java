package com.metropolitan.dtos;


import com.metropolitan.enums.TipGoriva;
import com.metropolitan.enums.VrstaAutomobila;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AutomobilDTO {
    private String model;
    private String brend;
    private int godinaProizvodnje;
    private double cenaPoDanu;
    private String statusVozila;
    private double kilometraza;
    private String opis;
    private int salon_id;
    private int brojVrata;
    private String tipGoriva;
    private int brojSedista;
    private String vrstaAutomobila;
}
