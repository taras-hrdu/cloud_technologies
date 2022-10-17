package com.hrudzinskyi.controller;

import com.hrudzinskyi.DTO.RepairsWorkerDTO;
import com.hrudzinskyi.mapper.RepairsWorkerMapper;
import com.hrudzinskyi.mapper.UserMapper;
import com.hrudzinskyi.model.RepairsWorker;
import com.hrudzinskyi.service.RepairsWorkerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/repairs_worker")
public class RepairsWorkerController {
    private final RepairsWorkerService repairsWorkerService;

    @GetMapping
    public List<RepairsWorkerDTO> getAllRepairsWorker() {
        return repairsWorkerService.getAllRepairsWorker().stream()
                .map(RepairsWorkerMapper::mapRepairsWorkerToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepairsWorkerDTO> getRepairsWorkerById(@PathVariable("id") Integer id) {
        RepairsWorker repairsWorker = repairsWorkerService.getRepairsWorkerById(id);
        if (repairsWorker == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                RepairsWorkerMapper.mapRepairsWorkerToDTO(repairsWorker), HttpStatus.OK);
    }

    @PostMapping
    public RepairsWorkerDTO createRepairsWorker(@RequestBody RepairsWorkerDTO repairsWorkerDTO) {
        return RepairsWorkerMapper.
                mapRepairsWorkerToDTO(repairsWorkerService.createRepairsWorker(repairsWorkerDTO));
    }

    @PutMapping
    public ResponseEntity<RepairsWorkerDTO> updateRepairsWorker(@RequestBody RepairsWorkerDTO repairsWorkerDTO) {
        RepairsWorker repairsWorker = repairsWorkerService.
                getRepairsWorkerById(repairsWorkerDTO.getId());
        if (repairsWorker == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                RepairsWorkerMapper.
                        mapRepairsWorkerToDTO(repairsWorkerService.
                                updateRepairsWorker(repairsWorkerDTO)), HttpStatus.OK
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<RepairsWorkerDTO> deleteRepairsWorker(@PathVariable("id") Integer id) {
        RepairsWorker repairsWorker = repairsWorkerService.getRepairsWorkerById(id);
        if (repairsWorker == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                RepairsWorkerMapper.mapRepairsWorkerToDTO(repairsWorkerService.
                        deleteRepairsWorkerById(id)), HttpStatus.OK
        );
    }

}