package com.programacion.distribuida.paralela.veterinary.dto;

import com.programacion.distribuida.paralela.veterinary.model.Pet;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Builder(toBuilder = true)
@Getter
@Setter
public class ClientDto {
    private Long id;
    private Long clientId;
    private String name;
    private String lastName;
    private String residenceAddress;
    private int phoneNumber;
}
