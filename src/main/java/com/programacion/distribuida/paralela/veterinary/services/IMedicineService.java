package com.programacion.distribuida.paralela.veterinary.services;

import com.programacion.distribuida.paralela.veterinary.response.MedicineResponseRest;
import org.springframework.http.ResponseEntity;

public interface IMedicineService {
    public ResponseEntity<MedicineResponseRest> search();
    public ResponseEntity<MedicineResponseRest> searchById(Long id);

}
