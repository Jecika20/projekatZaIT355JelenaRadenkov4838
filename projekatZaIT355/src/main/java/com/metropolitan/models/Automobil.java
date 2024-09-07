package com.metropolitan.models;

import com.metropolitan.enums.TipGoriva;
import com.metropolitan.enums.VrstaAutomobila;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name="id",referencedColumnName = "id")
public class Automobil extends  Vozilo {
    private int brojVrata;
    private TipGoriva tipGoriva;
    private int brojSedista;
    private VrstaAutomobila vrstaAutomobila;

}
