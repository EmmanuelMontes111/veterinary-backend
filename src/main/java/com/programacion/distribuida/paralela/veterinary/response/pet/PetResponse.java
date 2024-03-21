package com.programacion.distribuida.paralela.veterinary.response.pet;

import com.programacion.distribuida.paralela.veterinary.model.Pet;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PetResponse {

    private List<Pet> pets;

}
