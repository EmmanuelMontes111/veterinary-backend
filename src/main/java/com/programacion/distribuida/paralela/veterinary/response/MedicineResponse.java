package com.programacion.distribuida.paralela.veterinary.response;

import com.programacion.distribuida.paralela.veterinary.model.Medicine;
import lombok.Data;

import java.util.List;

@Data
public class MedicineResponse {

    private List<Medicine> medicines;
}
