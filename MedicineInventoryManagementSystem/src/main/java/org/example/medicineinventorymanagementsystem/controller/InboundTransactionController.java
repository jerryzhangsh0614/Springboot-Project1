package org.example.medicineinventorymanagementsystem.controller;

import jakarta.validation.Valid;
import org.example.medicineinventorymanagementsystem.model.InboundTransaction;
import org.example.medicineinventorymanagementsystem.service.InboundTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/inbound")
public class InboundTransactionController {

// Add Inbound Transactions[Batch] (POST /inbound/transactions)
// Get Inbound Transactions (GET /inbound/transactions?page={page}&size={size})

    @Autowired
    private InboundTransactionService inboundTransactionService;

    @PostMapping("/transactions")
    public ResponseEntity<String> createInboundTransaction(@Valid @RequestBody List<InboundTransaction> inboundTransaction, BindingResult bindingResult) {
        try{
            if (bindingResult.hasErrors()) {
                for (FieldError error : bindingResult.getFieldErrors()) {
                    System.out.println(error.getField() + " - " + error.getRejectedValue());
                }
                throw new RuntimeException("Bad Request - Post InboundTransactions Failed!");
            }
            inboundTransactionService.addInboundTransactions(inboundTransaction);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().body("Successfully created inbound transactions");
    }

    @GetMapping
    public Page<InboundTransaction> getInboundTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return inboundTransactionService.getInboundTransactions(page, size);
    }

}
