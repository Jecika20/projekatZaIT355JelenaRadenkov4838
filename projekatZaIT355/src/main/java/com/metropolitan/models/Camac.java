package com.metropolitan.models;

import com.metropolitan.enums.TipCamca;
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
public class Camac extends  Vozilo{
    private double duzina;
    private TipCamca tipCamca;
}
