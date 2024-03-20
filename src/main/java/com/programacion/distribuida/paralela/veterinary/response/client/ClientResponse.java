package com.programacion.distribuida.paralela.veterinary.response.client;

import com.programacion.distribuida.paralela.veterinary.dto.ClientDto;
import com.programacion.distribuida.paralela.veterinary.model.Client;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ClientResponse {

    private List<ClientDto> clients;
}
