package org.example.medicineinventorymanagementsystem.repository;

import org.example.medicineinventorymanagementsystem.model.InboundTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository
// all the CRUD operations, pagination, and sorting functionalities in a single repository interface
public interface InboundTransactionRepository extends JpaRepository<InboundTransaction, Integer> {
}
