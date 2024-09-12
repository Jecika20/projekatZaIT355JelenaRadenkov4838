package com.metropolitan.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SlikeVozila {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String slika;

    @ManyToOne
    @JoinColumn(name="vozilo_id")
    private Vozilo vozilo;

}
