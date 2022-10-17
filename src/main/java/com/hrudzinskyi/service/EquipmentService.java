package com.hrudzinskyi.service;

import com.hrudzinskyi.DTO.EquipmentDTO;
import com.hrudzinskyi.mapper.EquipmentMapper;
import com.hrudzinskyi.model.Equipment;
import com.hrudzinskyi.repository.EquipmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    public Equipment getEquipmentById(Integer id) {
        return equipmentRepository.findById(id).orElse(null);
    }

    public Equipment createEquipment(EquipmentDTO equipmentDTO) {
        return equipmentRepository.save(EquipmentMapper.mapDTOToEquipment(equipmentDTO));
    }

    public Equipment updateEquipment(EquipmentDTO equipmentDTO) {
        if (getEquipmentById(equipmentDTO.getId()) != null) {
            return equipmentRepository.save(EquipmentMapper.mapDTOToEquipment(equipmentDTO));
        }
        return null;
    }

    public Equipment deleteEquipmentById(Integer id) {
        Equipment equipment = getEquipmentById(id);
        if (equipment != null) {
            equipmentRepository.deleteById(id);
            return equipment;
        }
        return null;
    }
}