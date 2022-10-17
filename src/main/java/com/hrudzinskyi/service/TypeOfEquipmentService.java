package com.hrudzinskyi.service;

import com.hrudzinskyi.DTO.TypeOfEquipmentDTO;
import com.hrudzinskyi.mapper.TypeOfEquipmentMapper;
import com.hrudzinskyi.model.TypeOfEquipment;
import com.hrudzinskyi.repository.TypeOfEquipmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TypeOfEquipmentService {

    private final TypeOfEquipmentRepository typeOfEquipmentRepository;

    public List<TypeOfEquipment> getAllTypeOfEquipment() {
        return typeOfEquipmentRepository.findAll();
    }

    public TypeOfEquipment getTypeOfEquipmentById(Integer id) {
        return typeOfEquipmentRepository.findById(id).orElse(null);
    }

    public TypeOfEquipment createTypeOfEquipment(TypeOfEquipmentDTO typeOfEquipmentDTO) {
        return typeOfEquipmentRepository.save(TypeOfEquipmentMapper.mapDTOToTypeOfEquipment(typeOfEquipmentDTO));
    }

    public TypeOfEquipment updateTypeOfEquipment(TypeOfEquipmentDTO typeOfEquipmentDTO) {
        if (getTypeOfEquipmentById(typeOfEquipmentDTO.getId()) != null) {
            return typeOfEquipmentRepository.save(TypeOfEquipmentMapper.mapDTOToTypeOfEquipment(typeOfEquipmentDTO));
        }
        return null;
    }

    public TypeOfEquipment deleteTypeOfEquipmentById(Integer id) {
        TypeOfEquipment typeOfEquipment = getTypeOfEquipmentById(id);
        if (typeOfEquipment != null) {
            typeOfEquipmentRepository.deleteById(id);
            return typeOfEquipment;
        }
        return null;
    }
}