package com.hrudzinskyi.controller;

import com.hrudzinskyi.DTO.EquipmentTransferDTO;
import com.hrudzinskyi.mapper.EquipmentTransferMapper;
import com.hrudzinskyi.model.EquipmentTransfer;
import com.hrudzinskyi.service.EquipmentTransferService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/equipment_transfer")
public class EquipmentTransferController {
    private final EquipmentTransferService equipmentTransferService;

    @GetMapping
    public List<EquipmentTransferDTO> getAllEquipmentTransfer() {
        return equipmentTransferService.getAllEquipmentTransfer().stream()
                .map(EquipmentTransferMapper::mapEquipmentTransferToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentTransferDTO> getEquipmentTransferById(@PathVariable("id") Integer id) {
        EquipmentTransfer equipmentTransfer = equipmentTransferService.getEquipmentTransferById(id);
        if (equipmentTransfer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                EquipmentTransferMapper.mapEquipmentTransferToDTO(equipmentTransfer), HttpStatus.OK);
    }

    @PostMapping
    public EquipmentTransferDTO createEquipmentTransfer(@RequestBody EquipmentTransferDTO equipmentTransferDTO) {
        return EquipmentTransferMapper.
                mapEquipmentTransferToDTO(equipmentTransferService.createEquipmentTransfer(equipmentTransferDTO));
    }

    @PutMapping
    public ResponseEntity<EquipmentTransferDTO> updateEquipmentTransfer(@RequestBody EquipmentTransferDTO equipmentTransferDTO) {
        EquipmentTransfer equipmentTransfer = equipmentTransferService.
                getEquipmentTransferById(equipmentTransferDTO.getId());
        if (equipmentTransfer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                EquipmentTransferMapper.
                        mapEquipmentTransferToDTO(equipmentTransferService.
                                updateEquipmentTransfer(equipmentTransferDTO)), HttpStatus.OK
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<EquipmentTransferDTO> deleteEquipmentTransfer(@PathVariable("id") Integer id) {
        EquipmentTransfer equipmentTransfer = equipmentTransferService.getEquipmentTransferById(id);
        if (equipmentTransfer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                EquipmentTransferMapper.mapEquipmentTransferToDTO(equipmentTransferService.
                        deleteEquipmentTransferById(id)), HttpStatus.OK
        );
    }

}