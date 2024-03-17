package com.programacion.distribuida.paralela.veterinary.services;

import com.programacion.distribuida.paralela.veterinary.dao.IMedicineDao;
import com.programacion.distribuida.paralela.veterinary.model.Medicine;
import com.programacion.distribuida.paralela.veterinary.response.MedicineResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MedicineServiceImpl implements IMedicineService {

    public static final String RESPONSE_ERROR = "Response ERROR";
    public static final String ID_NOT_FOUND = "ID NOT FOUND";
    public static final String RESPONSE_OK = "Response OK";
    @Autowired
    private IMedicineDao medicineDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<MedicineResponseRest> search() {
        MedicineResponseRest response = new MedicineResponseRest();

        try {
            List<Medicine> medicines = (List<Medicine>) medicineDao.findAll();

            response.getMedicineResponse().setMedicines(medicines);
            response.setMetadata(RESPONSE_OK, "200", "Response success");

        } catch (Exception exception) {
            response.setMetadata(RESPONSE_ERROR, "500", "Response failed");
            exception.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<MedicineResponseRest> searchById(Long id) {

        MedicineResponseRest response = new MedicineResponseRest();
        List<Medicine> medicines = (List<Medicine>) medicineDao.findAll();

        try {
            Optional<Medicine> medicineOptional = medicineDao.findById(id);

            if (medicineOptional.isPresent()) {
                medicines.add(medicineOptional.get());
                response.getMedicineResponse().setMedicines(medicines);
                response.setMetadata(RESPONSE_OK, "200", "Medicine found");
            } else {
                response.setMetadata(ID_NOT_FOUND, "404", "Medicine not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception exception) {
            response.setMetadata(RESPONSE_ERROR, "500", "Error when querying by id");
            exception.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<MedicineResponseRest> save(Medicine medicine) {
        MedicineResponseRest response = new MedicineResponseRest();
        List<Medicine> medicines = new ArrayList<>();

        try {
            Medicine medicineSaved = medicineDao.save(medicine);

            if (medicineSaved != null) {
                medicines.add(medicine);
                response.getMedicineResponse().setMedicines(medicines);
                response.setMetadata(RESPONSE_OK, "200", "Medicine save");
            } else {
                response.setMetadata(RESPONSE_ERROR, "400", "Medicine not save");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception exception) {
            response.setMetadata(RESPONSE_ERROR, "500", "Error save medicine");
            exception.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<MedicineResponseRest> update(Medicine medicine, Long id) {
        MedicineResponseRest response = new MedicineResponseRest();
        List<Medicine> medicines = new ArrayList<>();

        try {
            Optional<Medicine> medicineSearch = medicineDao.findById(id);

            if (medicineSearch.isPresent()) {
                medicineSearch.get().setName(medicine.getName());
                medicineSearch.get().setDescription(medicine.getDescription());
                medicineSearch.get().setDose(medicine.getDose());

                Medicine medicineToUpdate = medicineDao.save(medicineSearch.get());

                if (medicineToUpdate != null) {
                    medicines.add(medicineToUpdate);
                    response.getMedicineResponse().setMedicines(medicines);
                    response.setMetadata(RESPONSE_OK, "200", "Medicine update");
                }else {
                    response.setMetadata(RESPONSE_ERROR, "400", "Medicine not update");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }

            } else {
                response.setMetadata(RESPONSE_ERROR, "404", "Medicine not found, is not possible update");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception exception) {
            response.setMetadata(RESPONSE_ERROR, "500", "Error update medicine");
            exception.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
