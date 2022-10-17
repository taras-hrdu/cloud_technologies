package com.hrudzinskyi.service;

import com.hrudzinskyi.DTO.RepairDTO;
import com.hrudzinskyi.mapper.RepairMapper;
import com.hrudzinskyi.model.Repair;
import com.hrudzinskyi.repository.RepairRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RepairService {

    private final RepairRepository repairRepository;

    public List<Repair> getAllRepair() {
        return repairRepository.findAll();
    }

    public Repair getRepairById(Integer id) {
        return repairRepository.findById(id).orElse(null);
    }

    public Repair createRepair(RepairDTO repairDTO) {
        return repairRepository.save(RepairMapper.mapDTOToRepair(repairDTO));
    }

    public Repair updateRepair(RepairDTO repairDTO) {
        if (getRepairById(repairDTO.getId()) != null) {
            return repairRepository.save(RepairMapper.mapDTOToRepair(repairDTO));
        }
        return null;
    }

    public Repair deleteRepairById(Integer id) {
        Repair repair = getRepairById(id);
        if (repair != null) {
            repairRepository.deleteById(id);
            return repair;
        }
        return null;
    }
}