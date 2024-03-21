package com.programacion.distribuida.paralela.veterinary.response.medicine;

import com.programacion.distribuida.paralela.veterinary.model.Medicine;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MedicineResponse {

    private List<Medicine> medicines;
}
