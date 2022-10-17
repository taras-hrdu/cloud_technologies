package com.hrudzinskyi.repository;


import com.hrudzinskyi.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Integer> {
}