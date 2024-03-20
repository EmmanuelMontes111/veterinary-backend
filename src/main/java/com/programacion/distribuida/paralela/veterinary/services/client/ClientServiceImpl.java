package com.programacion.distribuida.paralela.veterinary.services.client;

import com.programacion.distribuida.paralela.veterinary.dao.IClientDao;
import com.programacion.distribuida.paralela.veterinary.dto.ClientDto;
import com.programacion.distribuida.paralela.veterinary.model.Client;
import com.programacion.distribuida.paralela.veterinary.model.Pet;
import com.programacion.distribuida.paralela.veterinary.response.client.ClientResponseRest;
import com.programacion.distribuida.paralela.veterinary.services.pet.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ClientServiceImpl implements IClientService {

    public static final String RESPONSE_ERROR = "Response ERROR";
    public static final String ID_NOT_FOUND = "ID NOT FOUND";
    public static final String RESPONSE_OK = "Response OK";
    @Autowired
    private IClientDao clientDao;
    @Autowired
    private IPetService petService;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ClientResponseRest> search() {
        ClientResponseRest response = new ClientResponseRest();

        try {
            List<ClientDto> clientDtos = new ArrayList<>();

           List<Client> clients = (List<Client>) clientDao.findAll();

            for (Client client: clients) {
                clientDtos.add(buildClientDto(client));
            }

            response.getClientResponse().setClients(clientDtos);
            response.setMetadata(RESPONSE_OK, "200", "Response success");

        } catch (Exception exception) {
            response.setMetadata(RESPONSE_ERROR, "500", "Response failed");
            exception.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ClientDto buildClientDto(Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .clientId(client.getClientId())
                .name(client.getName())
                .lastName(client.getLastName())
                .residenceAddress(client.getResidenceAddress())
                .phoneNumber(client.getPhoneNumber())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ClientResponseRest> searchById(Long id) {

        ClientResponseRest response = new ClientResponseRest();
        List<ClientDto> clientDtos = new ArrayList<>();
        List<Client> clients = new ArrayList<>();

        try {

            Optional<Client> clientOptional = clientDao.findById(id);

            if (clientOptional.isPresent()) {
                clients.add(clientOptional.get());

                for (Client client: clients) {
                    clientDtos.add(buildClientDto(client));
                }

                response.getClientResponse().setClients(clientDtos);
                response.setMetadata(RESPONSE_OK, "200", "Client found");
            } else {
                response.setMetadata(ID_NOT_FOUND, "404", "Client not found");
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
    public ResponseEntity<ClientResponseRest> save(Client clientSave) {
        ClientResponseRest response = new ClientResponseRest();
        List<ClientDto> clientDtos = new ArrayList<>();
        List<Client> clients = new ArrayList<>();

        try {
            Client clientSaved = clientDao.save(clientSave);

            if (clientSaved != null) {
                clients.add(clientSave);

                for (Client client: clients) {
                    clientDtos.add(buildClientDto(client));
                }

                response.getClientResponse().setClients(clientDtos);
                response.setMetadata(RESPONSE_OK, "200", "Client save");
            } else {
                response.setMetadata(RESPONSE_ERROR, "400", "Client not save");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception exception) {
            response.setMetadata(RESPONSE_ERROR, "500", "Error save client");
            exception.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ClientResponseRest> update(Client clientUpdate, Long id) {
        ClientResponseRest response = new ClientResponseRest();
        List<ClientDto> clientDtos = new ArrayList<>();
        List<Client> clients = new ArrayList<>();

        try {
            Optional<Client> clientSearch = clientDao.findById(id);

            if (clientSearch.isPresent()) {
                clientSearch.get().setName(clientUpdate.getName());
                clientSearch.get().setLastName(clientUpdate.getLastName());
                clientSearch.get().setResidenceAddress(clientUpdate.getResidenceAddress());
                clientSearch.get().setPhoneNumber(clientUpdate.getPhoneNumber());
                clientSearch.get().setPets(clientUpdate.getPets());

                Client clientToUpdate = clientDao.save(clientSearch.get());

                if (clientToUpdate != null) {
                    clients.add(clientToUpdate);

                    for (Client client: clients) {
                        clientDtos.add(buildClientDto(client));
                    }

                    response.getClientResponse().setClients(clientDtos);
                    response.setMetadata(RESPONSE_OK, "200", "Client update");
                } else {
                    response.setMetadata(RESPONSE_ERROR, "400", "Client not update");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }

            } else {
                response.setMetadata(RESPONSE_ERROR, "404", "Client not found, is not possible update");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception exception) {
            response.setMetadata(RESPONSE_ERROR, "500", "Error update client");
            exception.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClientResponseRest> deleteById(Long id) {
        ClientResponseRest response = new ClientResponseRest();

        try {
            Optional<Client> clientSearch = clientDao.findById(id);

            if (clientSearch.isPresent()) {
                clientDao.deleteById(id);
                response.setMetadata(RESPONSE_OK, "200", "Client Delete by id: " + id);
            } else {
                response.setMetadata(RESPONSE_ERROR, "404", "Client not found, is not possible delete");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception exception) {
            response.setMetadata(RESPONSE_ERROR, "500", "Error delete client");
            exception.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
