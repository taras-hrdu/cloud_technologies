package com.hrudzinskyi.controller;

import com.hrudzinskyi.DTO.TypeOfEquipmentDTO;
import com.hrudzinskyi.mapper.TypeOfEquipmentMapper;
import com.hrudzinskyi.model.TypeOfEquipment;
import com.hrudzinskyi.service.TypeOfEquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/type_of_equipment")
public class TypeOfEquipmentController {
    private final TypeOfEquipmentService typeOfEquipmentService;

    @GetMapping
    public List<TypeOfEquipmentDTO> getAllTypeOfEquipment() {
        return typeOfEquipmentService.getAllTypeOfEquipment().stream().map(TypeOfEquipmentMapper::mapTypeOfEquipmentToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeOfEquipmentDTO> getTypeOfEquipmentById(@PathVariable("id") Integer id) {
        TypeOfEquipment typeOfEquipment = typeOfEquipmentService.getTypeOfEquipmentById(id);
        if (typeOfEquipment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                TypeOfEquipmentMapper.mapTypeOfEquipmentToDTO(typeOfEquipment), HttpStatus.OK);
    }

    @PostMapping
    public TypeOfEquipmentDTO createTypeOfEquipment(@RequestBody TypeOfEquipmentDTO typeOfEquipmentDTO) {
        return TypeOfEquipmentMapper.mapTypeOfEquipmentToDTO(typeOfEquipmentService.createTypeOfEquipment(typeOfEquipmentDTO));
    }

    @PutMapping
    public ResponseEntity<TypeOfEquipmentDTO> updateTypeOfEquipment(@RequestBody TypeOfEquipmentDTO typeOfEquipmentDTO) {
        TypeOfEquipment typeOfEquipment = typeOfEquipmentService.getTypeOfEquipmentById(typeOfEquipmentDTO.getId());
        if (typeOfEquipment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                TypeOfEquipmentMapper.mapTypeOfEquipmentToDTO(typeOfEquipmentService.updateTypeOfEquipment(typeOfEquipmentDTO)), HttpStatus.OK
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<TypeOfEquipmentDTO> deleteTypeOfEquipment(@PathVariable("id") Integer id) {
        TypeOfEquipment typeOfEquipment = typeOfEquipmentService.getTypeOfEquipmentById(id);
        if (typeOfEquipment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                TypeOfEquipmentMapper.mapTypeOfEquipmentToDTO(typeOfEquipmentService.deleteTypeOfEquipmentById(id)), HttpStatus.OK
        );
    }

}