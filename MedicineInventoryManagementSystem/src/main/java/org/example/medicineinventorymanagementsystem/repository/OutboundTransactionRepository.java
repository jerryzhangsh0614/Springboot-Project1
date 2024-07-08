package org.example.medicineinventorymanagementsystem.repository;

import org.example.medicineinventorymanagementsystem.model.InboundTransaction;
import org.example.medicineinventorymanagementsystem.model.OutboundTransaction;
import org.springframework.data.jpa.repository.JpaRepository;


// JpaRepository
// all the CRUD operations, pagination, and sorting functionalities in a single repository interface
public interface OutboundTransactionRepository  extends JpaRepository<OutboundTransaction, Integer> {
}