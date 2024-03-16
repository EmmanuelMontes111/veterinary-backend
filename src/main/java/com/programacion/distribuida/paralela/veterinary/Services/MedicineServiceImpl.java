package com.programacion.distribuida.paralela.veterinary.Services;

import com.programacion.distribuida.paralela.veterinary.response.MedicineResponseRest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MedicineServiceImpl implements IMedicineService {

    @Override
    public ResponseEntity<MedicineResponseRest> search() {
        return null;
    }
}
