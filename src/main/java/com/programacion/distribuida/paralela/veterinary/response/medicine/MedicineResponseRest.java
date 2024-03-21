package com.programacion.distribuida.paralela.veterinary.response.medicine;

import com.programacion.distribuida.paralela.veterinary.response.ResponseRest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicineResponseRest extends ResponseRest {

    private MedicineResponse medicineResponse = new MedicineResponse();

}
