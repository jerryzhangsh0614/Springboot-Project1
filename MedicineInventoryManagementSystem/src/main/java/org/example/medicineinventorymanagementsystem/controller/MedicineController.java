package org.example.medicineinventorymanagementsystem.controller;

import jakarta.validation.Valid;
import org.example.medicineinventorymanagementsystem.model.Medicine;
import org.example.medicineinventorymanagementsystem.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/medicines")
public class MedicineController {

    //    Add Medicine (POST /medicines)
    //    Get All Medicines (GET /medicines?page={page}&size={size})
    //    Get Medicine Details (GET /medicines/{id})
    //    Update Medicine (PUT /medicines/{id})
    //    Delete Medicine (DELETE /medicines/{id})

    private final MedicineService medicineService;

    @Autowired
    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    // @Valid 配合 BindingResult， 获取当前对象内容所以错误的信息
    @PostMapping
    public ResponseEntity<Medicine> addMedicine(@Valid @RequestBody Medicine medicine, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                System.out.println(error.getField() + " - " + error.getRejectedValue());
            }
            throw new RuntimeException("Bad Request - Add Medicine Failed!");
        }
        return ResponseEntity.ok(medicineService.createMedicine(medicine));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Medicine>> addMedicines(@Valid @RequestBody List<Medicine> medicines, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                System.out.println(error.getField() + " - " + error.getRejectedValue());
            }
            throw new RuntimeException("Bad Request - Add Medicine Batch Error!");
        }
        return ResponseEntity.ok(medicineService.createMedicines(medicines));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable Integer id) {
        return ResponseEntity.ok(medicineService.getMedicineById(id));
    }

    @GetMapping
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        List<Medicine> medicines = medicineService.getAllMedicines();
        return ResponseEntity.ok(medicines);
    }

    // update the whole object
    @PutMapping
    public ResponseEntity<Medicine> updateMedicine(@RequestBody Medicine medicine) {
        return ResponseEntity.ok(medicineService.saveOrupdateMedicine(medicine));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMedicine(@PathVariable Integer id) {
        return ResponseEntity.ok(medicineService.deleteMedicine(id));
    }
}
