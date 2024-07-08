package org.example.medicineinventorymanagementsystem.repository;

import org.example.medicineinventorymanagementsystem.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// MedicineRepository继承JpaRepository以后
// 把MedicineRepository 注入到Service里，使用很多内置方法
// @Repository
// 如果注入Repository， controller可以直接使用内置方法？ service层不用写？
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
    Optional<Medicine> findByName(String name);
}
