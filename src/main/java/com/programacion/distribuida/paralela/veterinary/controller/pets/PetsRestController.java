package com.programacion.distribuida.paralela.veterinary.controller.pets;

import com.programacion.distribuida.paralela.veterinary.model.Pet;
import com.programacion.distribuida.paralela.veterinary.response.pet.PetResponseRest;
import com.programacion.distribuida.paralela.veterinary.services.pet.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class PetsRestController {

    @Autowired
    private IPetService service;

    @GetMapping("/pets")
    public ResponseEntity<PetResponseRest> searchPets() {
        return service.search();
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<PetResponseRest> searchPetsById(@PathVariable Long id) {
        return service.searchById(id);
    }

    @PostMapping("/pets")
    public ResponseEntity<PetResponseRest> save(
            @RequestParam String name,
            @RequestParam String breed,
            @RequestParam int age,
            @RequestParam float weight,
            @RequestParam Long clientId) throws IOException {

        Pet pet = new Pet();
        pet.setName(name);
        pet.setBreed(breed);
        pet.setAge(age);
        pet.setWeight(weight);

        return service.save(pet, clientId);
    }

    @PutMapping("/pets/{id}")
    public ResponseEntity<PetResponseRest> update(@RequestBody Pet client, @PathVariable Long id) {
        return service.update(client, id);
    }

    @DeleteMapping("/pets/{id}")
    public ResponseEntity<PetResponseRest> delete(@PathVariable Long id) {
        return service.deleteById(id);
    }

}
