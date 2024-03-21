package com.programacion.distribuida.paralela.veterinary.dao;

import com.programacion.distribuida.paralela.veterinary.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface IClientDao extends CrudRepository<Client, Long> {


}
