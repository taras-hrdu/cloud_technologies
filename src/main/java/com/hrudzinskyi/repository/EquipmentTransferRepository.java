package com.hrudzinskyi.repository;


import com.hrudzinskyi.model.EquipmentTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentTransferRepository extends JpaRepository<EquipmentTransfer, Integer> {
}