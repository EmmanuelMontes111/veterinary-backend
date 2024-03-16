package com.programacion.distribuida.paralela.veterinary.controller;

import com.programacion.distribuida.paralela.veterinary.response.MedicineResponseRest;
import com.programacion.distribuida.paralela.veterinary.services.IMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MedicinesRestController {

    @Autowired
    private IMedicineService service;

    @GetMapping("/medicines")
    public ResponseEntity<MedicineResponseRest> searchMedicines() {

        ResponseEntity<MedicineResponseRest> response = service.search();

        return response;
    }

    @GetMapping("/medicines/{id}")
    public ResponseEntity<MedicineResponseRest> searchMedicinesById(@PathVariable Long id) {

        ResponseEntity<MedicineResponseRest> response = service.searchById(id);

        return response;
    }

}
