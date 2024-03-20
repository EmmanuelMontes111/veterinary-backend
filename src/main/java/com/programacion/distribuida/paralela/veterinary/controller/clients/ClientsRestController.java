package com.programacion.distribuida.paralela.veterinary.controller.clients;

import com.programacion.distribuida.paralela.veterinary.model.Client;

import com.programacion.distribuida.paralela.veterinary.response.client.ClientResponseRest;
import com.programacion.distribuida.paralela.veterinary.services.client.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ClientsRestController {

    @Autowired
    private IClientService service;

    @GetMapping("/clients")
    public ResponseEntity<ClientResponseRest> searchClients() {
        return service.search();
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientResponseRest> searchClientsById(@PathVariable Long id) {
        return service.searchById(id);
    }

    @PostMapping("/clients")
    public ResponseEntity<ClientResponseRest> save(@RequestBody Client client) {
        return service.save(client);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<ClientResponseRest> update(@RequestBody Client client, @PathVariable Long id) {
        return service.update(client, id);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<ClientResponseRest> delete(@PathVariable Long id) {
        return service.deleteById(id);
    }

}
