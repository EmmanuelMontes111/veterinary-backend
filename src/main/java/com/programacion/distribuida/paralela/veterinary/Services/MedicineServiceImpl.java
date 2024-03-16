package com.programacion.distribuida.paralela.veterinary.Services;

import com.programacion.distribuida.paralela.veterinary.dao.IMedicineDao;
import com.programacion.distribuida.paralela.veterinary.model.Medicine;
import com.programacion.distribuida.paralela.veterinary.response.MedicineResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class MedicineServiceImpl implements IMedicineService {

    @Autowired
    private IMedicineDao medicineDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<MedicineResponseRest> search() {
        MedicineResponseRest response = new MedicineResponseRest();

        try {
            List<Medicine> medicines = (List<Medicine>) medicineDao.findAll();

            response.getMedicineResponse().setMedicines(medicines);
            response.setMetadata("Response OK", "200", "Response success");

        } catch (Exception exception) {
            response.setMetadata("Response ERROR", "500", "Response failed");
            exception.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
