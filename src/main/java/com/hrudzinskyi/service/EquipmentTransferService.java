package com.hrudzinskyi.service;

import com.hrudzinskyi.DTO.EquipmentTransferDTO;
import com.hrudzinskyi.mapper.EquipmentTransferMapper;
import com.hrudzinskyi.model.EquipmentTransfer;
import com.hrudzinskyi.repository.EquipmentTransferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EquipmentTransferService {

    private final EquipmentTransferRepository equipmentTransferRepository;

    public List<EquipmentTransfer> getAllEquipmentTransfer() {
        return equipmentTransferRepository.findAll();
    }

    public EquipmentTransfer getEquipmentTransferById(Integer id) {
        return equipmentTransferRepository.findById(id).orElse(null);
    }

    public EquipmentTransfer createEquipmentTransfer(EquipmentTransferDTO equipmentTransferDTO) {
        return equipmentTransferRepository.save(EquipmentTransferMapper.mapDTOToEquipmentTransfer(equipmentTransferDTO));
    }

    public EquipmentTransfer updateEquipmentTransfer(EquipmentTransferDTO equipmentTransferDTO) {
        if (getEquipmentTransferById(equipmentTransferDTO.getId()) != null) {
            return equipmentTransferRepository.save(EquipmentTransferMapper.mapDTOToEquipmentTransfer(equipmentTransferDTO));
        }
        return null;
    }

    public EquipmentTransfer deleteEquipmentTransferById(Integer id) {
        EquipmentTransfer equipmentTransfer = getEquipmentTransferById(id);
        if (equipmentTransfer != null) {
            equipmentTransferRepository.deleteById(id);
            return equipmentTransfer;
        }
        return null;
    }
}