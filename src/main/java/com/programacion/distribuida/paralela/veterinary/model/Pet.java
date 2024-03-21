package com.programacion.distribuida.paralela.veterinary.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "pet")
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long id;
    private String name;
    private String breed;
    private int age;
    private float weight;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;
    @OneToMany
    @JoinTable(
            name = "pet_medicine",
            joinColumns = @JoinColumn(name = "pet_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    private List<Medicine> medicines;

}
