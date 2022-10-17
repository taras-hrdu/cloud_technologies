package com.hrudzinskyi.controller;

import com.hrudzinskyi.DTO.RepairDTO;
import com.hrudzinskyi.mapper.RepairMapper;
import com.hrudzinskyi.model.Repair;
import com.hrudzinskyi.service.RepairService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/repair")
public class RepairController {
    private final RepairService repairService;

    @GetMapping
    public List<RepairDTO> getAllRepair() {
        return repairService.getAllRepair().stream()
                .map(RepairMapper::mapRepairToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepairDTO> getRepairById(@PathVariable("id") Integer id) {
        Repair repair = repairService.getRepairById(id);
        if (repair == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                RepairMapper.mapRepairToDTO(repair), HttpStatus.OK);
    }

    @PostMapping
    public RepairDTO createRepair(@RequestBody RepairDTO repairDTO) {
        return RepairMapper.
                mapRepairToDTO(repairService.createRepair(repairDTO));
    }

    @PutMapping
    public ResponseEntity<RepairDTO> updateRepair(@RequestBody RepairDTO repairDTO) {
        Repair repair = repairService.
                getRepairById(repairDTO.getId());
        if (repair == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                RepairMapper.
                        mapRepairToDTO(repairService.
                                updateRepair(repairDTO)), HttpStatus.OK
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<RepairDTO> deleteRepair(@PathVariable("id") Integer id) {
        Repair repair = repairService.getRepairById(id);
        if (repair == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                RepairMapper.mapRepairToDTO(repairService.
                        deleteRepairById(id)), HttpStatus.OK
        );
    }

}