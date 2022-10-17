package com.hrudzinskyi.mapper;

import com.hrudzinskyi.DTO.EquipmentDTO;
import com.hrudzinskyi.model.Equipment;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EquipmentMapper {
    public static EquipmentDTO mapEquipmentToDTO(Equipment equipment) {
        return new EquipmentDTO(
                equipment.getId(),
                equipment.getInventoryNumbers(),
                equipment.getColor(),
                equipment.getBodyMaterial(),
                equipment.getConditionOfEquipment()
        );
    }

    public static Equipment mapDTOToEquipment(EquipmentDTO equipmentDTO) {
        return new Equipment(
                equipmentDTO.getId(),
                equipmentDTO.getInventoryNumbers(),
                equipmentDTO.getColor(),
                equipmentDTO.getBodyMaterial(),
                equipmentDTO.getConditionOfEquipment()
        );
    }
}