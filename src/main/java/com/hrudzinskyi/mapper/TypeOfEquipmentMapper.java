package com.hrudzinskyi.mapper;

import com.hrudzinskyi.DTO.TypeOfEquipmentDTO;
import com.hrudzinskyi.model.TypeOfEquipment;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TypeOfEquipmentMapper {

    public static TypeOfEquipmentDTO mapTypeOfEquipmentToDTO(TypeOfEquipment typeOfEquipment) {
        return new TypeOfEquipmentDTO(
                typeOfEquipment.getId(),
                typeOfEquipment.getType()
        );
    }

    public static TypeOfEquipment mapDTOToTypeOfEquipment(TypeOfEquipmentDTO typeOfEquipmentDTO) {
        return new TypeOfEquipment(
                typeOfEquipmentDTO.getId(),
                typeOfEquipmentDTO.getType()
        );
    }
}