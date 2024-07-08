package org.example.medicineinventorymanagementsystem.controller;

import jakarta.validation.Valid;
import org.example.medicineinventorymanagementsystem.model.InboundTransaction;
import org.example.medicineinventorymanagementsystem.model.OutboundTransaction;
import org.example.medicineinventorymanagementsystem.service.OutboundTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/outbound")
public class OutboundTransactionController {
    // Add Outbound Transaction[Batch] (POST /outbound/transactions)
    // Get Outbound Transactions (GET /outbound/transactions?page={page}&size={size})
    @Autowired
    private OutboundTransactionService outboundTransactionService;

    @PostMapping("/transactions")
    public ResponseEntity<String> createOutboundTransaction(@Valid @RequestBody List<OutboundTransaction> outboundTransactions, BindingResult bindingResult) {
        try{
            if (bindingResult.hasErrors()) {
                for (FieldError error : bindingResult.getFieldErrors()) {
                    System.out.println(error.getField() + " - " + error.getRejectedValue());
                }
                throw new RuntimeException("Bad Request - Post OutboundTransactions Failed!");
            }
            outboundTransactionService.addOutboundTransactions(outboundTransactions);

        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Successfully created outbound transactions");
    }

    @GetMapping
    public Page<OutboundTransaction> getOutboundTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return outboundTransactionService.getOutboundTransactions(page, size);
    }
}
