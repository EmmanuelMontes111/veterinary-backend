package com.programacion.distribuida.paralela.veterinary.response.pet;

import com.programacion.distribuida.paralela.veterinary.response.ResponseRest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetResponseRest extends ResponseRest {

    private PetResponse petResponse = new PetResponse();

}
