package com.programacion.distribuida.paralela.veterinary.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "client")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String residenceAddress;
    private int phoneNumber;
    @OneToMany(mappedBy = "client")
    private List<Pet> pets;
}
