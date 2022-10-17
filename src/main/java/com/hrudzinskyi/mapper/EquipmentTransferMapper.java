package com.hrudzinskyi.mapper;

import com.hrudzinskyi.DTO.EquipmentTransferDTO;
import com.hrudzinskyi.model.EquipmentTransfer;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EquipmentTransferMapper {
    public static EquipmentTransferDTO mapEquipmentTransferToDTO(EquipmentTransfer equipmentTransfer) {
        return new EquipmentTransferDTO(
                equipmentTransfer.getId(),
                equipmentTransfer.getTime(),
                equipmentTransfer.getStartData(),
                equipmentTransfer.getEndData()
        );
    }

    public static EquipmentTransfer mapDTOToEquipmentTransfer(EquipmentTransferDTO equipmentTransferDTO) {
        return new EquipmentTransfer(
                equipmentTransferDTO.getId(),
                equipmentTransferDTO.getTime(),
                equipmentTransferDTO.getStartData(),
                equipmentTransferDTO.getEndData()
        );
    }
}