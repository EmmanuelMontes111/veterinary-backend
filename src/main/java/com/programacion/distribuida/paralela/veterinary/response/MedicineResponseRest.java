package com.programacion.distribuida.paralela.veterinary.response;

import lombok.Setter;

@Setter
public class MedicineResponseRest extends ResponseRest {

    private MedicineResponse medicineResponse = new MedicineResponse();

    public MedicineResponse getMedicineResponse() {
        return this.medicineResponse;
    }
}
