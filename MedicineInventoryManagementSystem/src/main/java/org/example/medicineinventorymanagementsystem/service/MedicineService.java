package org.example.medicineinventorymanagementsystem.service;

import org.example.medicineinventorymanagementsystem.model.Medicine;
import org.example.medicineinventorymanagementsystem.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {

    // using final, we need constructor instead of autowired injection
    private final MedicineRepository medicineRepository;

    @Autowired
    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    // add medicine
    public Medicine createMedicine(Medicine medicine){
        // check if name already exists
        Optional<Medicine> medicineExists = medicineRepository.findByName(medicine.getName());

        if(medicineExists.isPresent()){
            throw new IllegalArgumentException("Medicine with the name " + medicine.getName() + " already exists.");
        }
        return medicineRepository.save(medicine);
    }

    // add medicines batch
    public List<Medicine> createMedicines(List<Medicine> medicines) {
        // check if exists
        medicines.forEach(medicine -> {
            Optional<Medicine> medicineExists = medicineRepository.findByName(medicine.getName());
            if(medicineExists.isPresent()){
                throw new IllegalArgumentException("Medicine with the name " + medicine.getName() + " already exists.");
            }
        });
        return medicineRepository.saveAll(medicines);
    }

    // get medicine by id
    public Medicine getMedicineById(Integer id) {
        return medicineRepository.findById(id).orElseThrow(() -> new RuntimeException("Medicine id not found"));
    }

    // get all medicines
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    // update medicine
    @Transactional
    public Medicine saveOrupdateMedicine(Medicine medicine) {
        Optional<Medicine> existingMedicine = medicineRepository.findByName(medicine.getName());

        if (existingMedicine.isPresent()) {
            Medicine updateMedicine = existingMedicine.get();
            updateMedicine.setDescription(medicine.getDescription());
            updateMedicine.setQuantity(medicine.getQuantity());
            updateMedicine.setType(medicine.getType());
            return medicineRepository.save(updateMedicine);
        } else {
            return medicineRepository.save(medicine);
        }
    }

    // delete medicine
    public Boolean deleteMedicine(Integer id) {
        medicineRepository.deleteById(id);
        return true;
    }

    // update quantity of the medicine


}
