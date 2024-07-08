package org.example.medicineinventorymanagementsystem.service;

import org.example.medicineinventorymanagementsystem.model.InboundTransaction;
import org.example.medicineinventorymanagementsystem.model.Medicine;
import org.example.medicineinventorymanagementsystem.model.OutboundTransaction;
import org.example.medicineinventorymanagementsystem.repository.MedicineRepository;
import org.example.medicineinventorymanagementsystem.repository.OutboundTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OutboundTransactionService {
    @Autowired
    private OutboundTransactionRepository outboundTransactionRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Transactional
    public void addOutboundTransactions(List<OutboundTransaction> outboundTransactions) {
        for (OutboundTransaction transaction : outboundTransactions) {
            // Fetch the existing medicine entity using medicineId
            Medicine medicine = medicineRepository.findById(transaction.getMedicineId())
                    .orElseThrow(() -> new RuntimeException("Medicine not found: " + transaction.getMedicineId()));

            // Check if there is enough quantity available
            if (medicine.getQuantity() < transaction.getQuantity()) {
                throw new RuntimeException("Not enough quantity for medicine ID: " + transaction.getMedicineId());
            }

            // Set the reference to the managed medicine entity in the transaction
            transaction.setMedicine(medicine);

            // Save the outbound transaction
            outboundTransactionRepository.save(transaction);

            // Deduct the quantity of the medicine
            medicine.setQuantity(medicine.getQuantity() - transaction.getQuantity());

            // Save the updated medicine
            medicineRepository.save(medicine);
        }
    }

    public Page<OutboundTransaction> getOutboundTransactions(int page, int size) {
        return outboundTransactionRepository.findAll(PageRequest.of(page, size));
    }
}