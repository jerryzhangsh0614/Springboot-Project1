package org.example.medicineinventorymanagementsystem.service;

import org.example.medicineinventorymanagementsystem.model.InboundTransaction;
import org.example.medicineinventorymanagementsystem.model.Medicine;
import org.example.medicineinventorymanagementsystem.repository.InboundTransactionRepository;
import org.example.medicineinventorymanagementsystem.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InboundTransactionService {

    @Autowired
    private InboundTransactionRepository inboundTransactionRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Transactional
    public void addInboundTransactions(List<InboundTransaction> inboundTransactions) {
        for (InboundTransaction transaction : inboundTransactions) {
            // find medicine obj with medicineId
            Medicine medicine = medicineRepository.findById(transaction.getMedicineId())
                    .orElseThrow(() -> new RuntimeException("Medicine not found: " + transaction.getMedicine().getId()));
            // System.out.println("Medicine: "+ medicine + " with quantity: " + medicine.getQuantity());
            // reference to the managed medicine entity in the transaction
            transaction.setMedicine(medicine);

            // Save the inbound transaction
            inboundTransactionRepository.save(transaction);

            // Update the medicine quantity
            medicine.setQuantity(medicine.getQuantity() + transaction.getQuantity());
        };
    }

    public Page<InboundTransaction> getInboundTransactions(int page, int size) {
        return inboundTransactionRepository.findAll(PageRequest.of(page, size));
    }


}
