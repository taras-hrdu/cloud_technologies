package com.hrudzinskyi.repository;


import com.hrudzinskyi.model.LaserCutter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaserCutterRepository extends JpaRepository<LaserCutter, Integer> {
}