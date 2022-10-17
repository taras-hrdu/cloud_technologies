package com.hrudzinskyi.service;

import com.hrudzinskyi.DTO.LaserCutterDTO;
import com.hrudzinskyi.mapper.LaserCutterMapper;
import com.hrudzinskyi.model.LaserCutter;
import com.hrudzinskyi.repository.LaserCutterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class LaserCutterService {

    private final LaserCutterRepository laserCutterRepository;

    public List<LaserCutter> getAllLaserCutter() {
        return laserCutterRepository.findAll();
    }

    public LaserCutter getLaserCutterById(Integer id) {
        return laserCutterRepository.findById(id).orElse(null);
    }

    public LaserCutter createLaserCutter(LaserCutterDTO laserCutterDTO) {
        return laserCutterRepository.save(LaserCutterMapper.mapDTOToLaserCutter(laserCutterDTO));
    }

    public LaserCutter updateLaserCutter(LaserCutterDTO laserCutterDTO) {
        if (getLaserCutterById(laserCutterDTO.getId()) != null) {
            return laserCutterRepository.save(LaserCutterMapper.mapDTOToLaserCutter(laserCutterDTO));
        }
        return null;
    }

    public LaserCutter deleteLaserCutterById(Integer id) {
        LaserCutter laserCutter = getLaserCutterById(id);
        if (laserCutter != null) {
            laserCutterRepository.deleteById(id);
            return laserCutter;
        }
        return null;
    }
}