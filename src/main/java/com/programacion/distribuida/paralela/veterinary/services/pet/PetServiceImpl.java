package com.programacion.distribuida.paralela.veterinary.services.pet;

import com.programacion.distribuida.paralela.veterinary.dao.IClientDao;
import com.programacion.distribuida.paralela.veterinary.dao.IPetDao;
import com.programacion.distribuida.paralela.veterinary.model.Client;
import com.programacion.distribuida.paralela.veterinary.model.Pet;
import com.programacion.distribuida.paralela.veterinary.response.pet.PetResponseRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PetServiceImpl implements IPetService {

    public static final String RESPONSE_ERROR = "Response ERROR";
    public static final String ID_NOT_FOUND = "ID NOT FOUND";
    public static final String RESPONSE_OK = "Response OK";

    private IClientDao clientDao;
    private IPetDao petDao;

    public PetServiceImpl(IClientDao clientDao, IPetDao petDao) {
        super();
        this.clientDao = clientDao;
        this.petDao = petDao;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<PetResponseRest> search() {
        PetResponseRest response = new PetResponseRest();

        try {
            List<Pet> pets = (List<Pet>) petDao.findAll();

            response.getPetResponse().setPets(pets);
            response.setMetadata(RESPONSE_OK, "200", "Response success");

        } catch (Exception exception) {
            response.setMetadata(RESPONSE_ERROR, "500", "Response failed");
            exception.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<PetResponseRest> searchById(Long id) {

        PetResponseRest response = new PetResponseRest();
        List<Pet> pets = new ArrayList<>();

        try {
            Optional<Pet> petOptional = petDao.findById(id);

            if (petOptional.isPresent()) {
                pets.add(petOptional.get());
                response.getPetResponse().setPets(pets);
                response.setMetadata(RESPONSE_OK, "200", "Pet found");
            } else {
                response.setMetadata(ID_NOT_FOUND, "404", "Pet not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception exception) {
            response.setMetadata(RESPONSE_ERROR, "500", "Error when querying by id");
            exception.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<PetResponseRest> save(Pet pet, Long clientId) {
        PetResponseRest response = new PetResponseRest();
        List<Pet> pets = new ArrayList<>();

        try {
            //buscar cliente para setearla en la mascota
            Optional<Client> client = clientDao.findById(clientId);

            if (client.isPresent()) {
                pet.setClient(client.get());
            } else {
                //Si no existe el cliente no puedo guardar una mascota
                response.setMetadata(RESPONSE_ERROR, "404", "Client not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            //guardar mascota
            Pet petSaved = petDao.save(pet);

            if (petSaved != null) {
                pets.add(pet);
                response.getPetResponse().setPets(pets);
                response.setMetadata(RESPONSE_OK, "200", "Pet save");
            } else {
                response.setMetadata(RESPONSE_ERROR, "400", "Pet not save");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception exception) {
            response.setMetadata(RESPONSE_ERROR, "500", "Error save pet");
            exception.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<PetResponseRest> update(Pet pet, Long clientId, Long id) {
        PetResponseRest response = new PetResponseRest();
        List<Pet> pets = new ArrayList<>();

        try {
            Optional<Client> clientOptional = clientDao.findById(clientId);

            if (clientOptional.isPresent()) {
                pet.setClient(clientOptional.get());
            } else {
                //Si no existe el cliente no puedo actualizar la mascota
                response.setMetadata(RESPONSE_ERROR, "404", "Client not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            //Bucar mascota a actualizar

            Optional<Pet> petSearch = petDao.findById(id);


            if (petSearch.isPresent()) {
                //se actaliza la mascota
                petSearch.get().setName(pet.getName());
                petSearch.get().setWeight(pet.getWeight());
                petSearch.get().setAge(pet.getAge());
                petSearch.get().setBreed(pet.getBreed());


                Pet petToUpdate = petDao.save(petSearch.get());

                if (petToUpdate != null) {
                    pets.add(petToUpdate);
                    response.getPetResponse().setPets(pets);
                    response.setMetadata(RESPONSE_OK, "200", "Pet update");
                } else {
                    response.setMetadata(RESPONSE_ERROR, "400", "Pet not update");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }

            } else {
                response.setMetadata(RESPONSE_ERROR, "404", "Pet not found, is not possible update");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception exception) {
            response.setMetadata(RESPONSE_ERROR, "500", "Error update pet");
            exception.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PetResponseRest> deleteById(Long id) {
        PetResponseRest response = new PetResponseRest();

        try {
            Optional<Pet> petSearch = petDao.findById(id);

            if (petSearch.isPresent()) {
                petDao.deleteById(id);
                response.setMetadata(RESPONSE_OK, "200", "Pet Delete by id: " + id);
            } else {
                response.setMetadata(RESPONSE_ERROR, "404", "Pet not found, is not possible delete");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception exception) {
            response.setMetadata(RESPONSE_ERROR, "500", "Error delete pet");
            exception.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
