package com.programacion.distribuida.paralela.veterinary.response.client;

import com.programacion.distribuida.paralela.veterinary.response.ResponseRest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientResponseRest extends ResponseRest {

    private ClientResponse clientResponse = new ClientResponse();

}
