package com.metropolitan.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CamacDTO {
    private String model;
    private String brend;
    private int godinaProizvodnje;
    private double cenaPoDanu;
    private String statusVozila;
    private double kilometraza;
    private String opis;
    private int salon_id;
    private double duzina;
    private String tipCamca;
}
