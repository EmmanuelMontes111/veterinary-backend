package com.programacion.distribuida.paralela.veterinary.response;

import com.programacion.distribuida.paralela.veterinary.model.Medicine;

import java.util.List;

public class MedicineResponse {

    private List<Medicine> medicines;

    public MedicineResponse() {
    }

    public List<Medicine> getMedicines() {
        return this.medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MedicineResponse)) return false;
        final MedicineResponse other = (MedicineResponse) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$medicines = this.getMedicines();
        final Object other$medicines = other.getMedicines();
        if (this$medicines == null ? other$medicines != null : !this$medicines.equals(other$medicines)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MedicineResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $medicines = this.getMedicines();
        result = result * PRIME + ($medicines == null ? 43 : $medicines.hashCode());
        return result;
    }

    public String toString() {
        return "MedicineResponse(medicines=" + this.getMedicines() + ")";
    }
}
