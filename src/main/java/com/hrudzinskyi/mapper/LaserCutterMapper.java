package com.hrudzinskyi.mapper;

import com.hrudzinskyi.DTO.LaserCutterDTO;
import com.hrudzinskyi.model.LaserCutter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LaserCutterMapper {
    public static LaserCutterDTO mapLaserCutterToDTO(LaserCutter laserCutter) {
        return new LaserCutterDTO(
                laserCutter.getId(),
                laserCutter.getTimeUsing(),
                laserCutter.getStartData(),
                laserCutter.getEndData(),
                laserCutter.getEquipmentId()
        );
    }

    public static LaserCutter mapDTOToLaserCutter(LaserCutterDTO laserCutterDTO) {
        return new LaserCutter(
                laserCutterDTO.getId(),
                laserCutterDTO.getTimeUsing(),
                laserCutterDTO.getStartData(),
                laserCutterDTO.getEndData(),
                laserCutterDTO.getEquipmentId()
        );
    }
}