package com.programacion.distribuida.paralela.veterinary.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "medicine")
public class Medicine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String dose;

    @ManyToMany(mappedBy = "medicines")
    private List<Pet> pets;
}
