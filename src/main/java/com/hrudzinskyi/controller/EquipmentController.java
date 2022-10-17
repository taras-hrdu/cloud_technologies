package com.hrudzinskyi.controller;

import com.hrudzinskyi.DTO.EquipmentDTO;
import com.hrudzinskyi.mapper.EquipmentMapper;
import com.hrudzinskyi.model.Equipment;
import com.hrudzinskyi.service.EquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/equipment")
public class EquipmentController {
    private final EquipmentService equipmentService;

    @GetMapping
    public List<EquipmentDTO> getAllEquipment() {
        return equipmentService.getAllEquipment().stream()
                .map(EquipmentMapper::mapEquipmentToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentDTO> getEquipmentById(@PathVariable("id") Integer id) {
        Equipment equipment = equipmentService.getEquipmentById(id);
        if (equipment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                EquipmentMapper.mapEquipmentToDTO(equipment), HttpStatus.OK);
    }

    @PostMapping
    public EquipmentDTO createEquipment(@RequestBody EquipmentDTO equipmentDTO) {
        return EquipmentMapper.
                mapEquipmentToDTO(equipmentService.createEquipment(equipmentDTO));
    }

    @PutMapping
    public ResponseEntity<EquipmentDTO> updateEquipment(@RequestBody EquipmentDTO equipmentDTO) {
        Equipment equipment = equipmentService.
                getEquipmentById(equipmentDTO.getId());
        if (equipment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                EquipmentMapper.
                        mapEquipmentToDTO(equipmentService.
                                updateEquipment(equipmentDTO)), HttpStatus.OK
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<EquipmentDTO> deleteEquipment(@PathVariable("id") Integer id) {
        Equipment equipment = equipmentService.getEquipmentById(id);
        if (equipment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                EquipmentMapper.mapEquipmentToDTO(equipmentService.
                        deleteEquipmentById(id)), HttpStatus.OK
        );
    }

}