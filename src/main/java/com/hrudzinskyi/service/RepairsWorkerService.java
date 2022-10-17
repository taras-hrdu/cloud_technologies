package com.hrudzinskyi.service;

import com.hrudzinskyi.DTO.RepairsWorkerDTO;
import com.hrudzinskyi.mapper.RepairsWorkerMapper;
import com.hrudzinskyi.model.RepairsWorker;
import com.hrudzinskyi.repository.RepairsWorkerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RepairsWorkerService {

    private final RepairsWorkerRepository repairsWorkerRepository;

    public List<RepairsWorker> getAllRepairsWorker() {
        return repairsWorkerRepository.findAll();
    }

    public RepairsWorker getRepairsWorkerById(Integer id) {
        return repairsWorkerRepository.findById(id).orElse(null);
    }

    public RepairsWorker createRepairsWorker(RepairsWorkerDTO repairsWorkerDTO) {
        return repairsWorkerRepository.save(RepairsWorkerMapper.mapDTOToRepairsWorker(repairsWorkerDTO));
    }

    public RepairsWorker updateRepairsWorker(RepairsWorkerDTO repairsWorkerDTO) {
        if (getRepairsWorkerById(repairsWorkerDTO.getId()) != null) {
            return repairsWorkerRepository.save(RepairsWorkerMapper.mapDTOToRepairsWorker(repairsWorkerDTO));
        }
        return null;
    }

    public RepairsWorker deleteRepairsWorkerById(Integer id) {
        RepairsWorker repairsWorker = getRepairsWorkerById(id);
        if (repairsWorker != null) {
            repairsWorkerRepository.deleteById(id);
            return repairsWorker;
        }
        return null;
    }
}