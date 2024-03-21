package com.programacion.distribuida.paralela.veterinary.dao;

import com.programacion.distribuida.paralela.veterinary.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface IPetDao extends CrudRepository<Pet, Long> {
}
