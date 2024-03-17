package com.programacion.distribuida.paralela.veterinary.controller;

import com.programacion.distribuida.paralela.veterinary.model.Medicine;
import com.programacion.distribuida.paralela.veterinary.response.MedicineResponseRest;
import com.programacion.distribuida.paralela.veterinary.services.IMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MedicinesRestController {

    @Autowired
    private IMedicineService service;

    @GetMapping("/medicines")
    public ResponseEntity<MedicineResponseRest> searchMedicines() {
        return service.search();
    }

    @GetMapping("/medicines/{id}")
    public ResponseEntity<MedicineResponseRest> searchMedicinesById(@PathVariable Long id) {
        return service.searchById(id);
    }

    @PostMapping("/medicines")
    public ResponseEntity<MedicineResponseRest> save(@RequestBody Medicine medicine) {
        return service.save(medicine);
    }

    @PutMapping("/medicines/{id}")
    public ResponseEntity<MedicineResponseRest> update(@RequestBody Medicine medicine, @PathVariable Long id) {
        return service.update(medicine, id);
    }

}
