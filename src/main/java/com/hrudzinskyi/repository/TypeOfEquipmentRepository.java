package com.hrudzinskyi.repository;

import com.hrudzinskyi.model.TypeOfEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfEquipmentRepository extends JpaRepository<TypeOfEquipment, Integer> {
}