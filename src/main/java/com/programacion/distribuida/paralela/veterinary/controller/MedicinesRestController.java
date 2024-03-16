package com.programacion.distribuida.paralela.veterinary.controller;

import com.programacion.distribuida.paralela.veterinary.Services.IMedicineService;
import com.programacion.distribuida.paralela.veterinary.response.MedicineResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MedicinesRestController {

    @Autowired
    private IMedicineService iMedicineService;

    @GetMapping("/medicines")
    public ResponseEntity<MedicineResponseRest> searchMedicines() {

        ResponseEntity<MedicineResponseRest> response = iMedicineService.search();

        return response;
    }

}
