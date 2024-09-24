package com.metropolitan.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAutomobilDTO {
    private int id;
    private String model;
    private String brend;
    private int godinaProizvodnje;
    private double cenaPoDanu;
    private String statusVozila;
    private double kilometraza;
    private String opis;
    private int brojVrata;
    private String tipGoriva;
    private int brojSedista;
    private String vrstaAutomobila;
}
