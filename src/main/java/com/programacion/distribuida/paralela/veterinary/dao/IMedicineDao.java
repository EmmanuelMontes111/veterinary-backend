package com.programacion.distribuida.paralela.veterinary.dao;

import com.programacion.distribuida.paralela.veterinary.model.Medicine;
import org.springframework.data.repository.CrudRepository;

public interface IMedicineDao extends CrudRepository<Medicine, Long> {


}
