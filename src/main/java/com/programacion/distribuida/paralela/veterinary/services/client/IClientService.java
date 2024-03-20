package com.programacion.distribuida.paralela.veterinary.services.client;

import com.programacion.distribuida.paralela.veterinary.model.Client;
import com.programacion.distribuida.paralela.veterinary.response.client.ClientResponseRest;
import org.springframework.http.ResponseEntity;

public interface IClientService {
    public ResponseEntity<ClientResponseRest> search();

    public ResponseEntity<ClientResponseRest> searchById(Long id);

    public ResponseEntity<ClientResponseRest> save(Client client);

    public ResponseEntity<ClientResponseRest> update(Client client, Long id);

    public ResponseEntity<ClientResponseRest> deleteById(Long id);
}
