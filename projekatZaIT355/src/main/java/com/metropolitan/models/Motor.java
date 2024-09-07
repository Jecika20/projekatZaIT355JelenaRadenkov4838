package com.metropolitan.models;

import com.metropolitan.enums.TipMotora;
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
public class Motor extends Vozilo {
    private int brojCilindara;
    private int brojTockova;
    private TipMotora tipMotora;
}
