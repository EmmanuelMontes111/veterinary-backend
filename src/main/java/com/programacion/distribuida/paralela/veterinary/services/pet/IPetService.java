package com.programacion.distribuida.paralela.veterinary.services.pet;

import com.programacion.distribuida.paralela.veterinary.model.Pet;
import com.programacion.distribuida.paralela.veterinary.response.pet.PetResponseRest;
import org.springframework.http.ResponseEntity;

public interface IPetService {
    public ResponseEntity<PetResponseRest> search();

    public ResponseEntity<PetResponseRest> searchById(Long id);

    public ResponseEntity<PetResponseRest> save(Pet client, Long clientId);

    public ResponseEntity<PetResponseRest> update(Pet client, Long id);

    public ResponseEntity<PetResponseRest> deleteById(Long id);
}
