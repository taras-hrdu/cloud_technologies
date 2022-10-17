package com.hrudzinskyi.repository;


import com.hrudzinskyi.model.RepairsWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairsWorkerRepository extends JpaRepository<RepairsWorker, Integer> {
}