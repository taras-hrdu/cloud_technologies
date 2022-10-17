package com.hrudzinskyi.controller;

import com.hrudzinskyi.DTO.LaserCutterDTO;
import com.hrudzinskyi.mapper.LaserCutterMapper;
import com.hrudzinskyi.model.LaserCutter;
import com.hrudzinskyi.service.LaserCutterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/laser_cutter")
public class LaserCutterController {
    private final LaserCutterService laserCutterService;

    @GetMapping
    public List<LaserCutterDTO> getAllLaserCutter() {
        return laserCutterService.getAllLaserCutter().stream()
                .map(LaserCutterMapper::mapLaserCutterToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaserCutterDTO> getLaserCutterById(@PathVariable("id") Integer id) {
        LaserCutter laserCutter = laserCutterService.getLaserCutterById(id);
        if (laserCutter == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                LaserCutterMapper.mapLaserCutterToDTO(laserCutter), HttpStatus.OK);
    }

    @PostMapping
    public LaserCutterDTO createLaserCutter(@RequestBody LaserCutterDTO laserCutterDTO) {
        return LaserCutterMapper.
                mapLaserCutterToDTO(laserCutterService.createLaserCutter(laserCutterDTO));
    }

    @PutMapping
    public ResponseEntity<LaserCutterDTO> updateLaserCutter(@RequestBody LaserCutterDTO laserCutterDTO) {
        LaserCutter laserCutter = laserCutterService.
                getLaserCutterById(laserCutterDTO.getId());
        if (laserCutter == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                LaserCutterMapper.
                        mapLaserCutterToDTO(laserCutterService.
                                updateLaserCutter(laserCutterDTO)), HttpStatus.OK
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<LaserCutterDTO> deleteLaserCutter(@PathVariable("id") Integer id) {
        LaserCutter laserCutter = laserCutterService.getLaserCutterById(id);
        if (laserCutter == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                LaserCutterMapper.mapLaserCutterToDTO(laserCutterService.
                        deleteLaserCutterById(id)), HttpStatus.OK
        );
    }

}