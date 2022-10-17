package com.hrudzinskyi.mapper;

import com.hrudzinskyi.DTO.RepairDTO;
import com.hrudzinskyi.model.Repair;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RepairMapper {
    public static RepairDTO mapRepairToDTO(Repair repair) {
        return new RepairDTO(
                repair.getId(),
                repair.getTime(),
                repair.getStartData(),
                repair.getEndData(),
                repair.getRepairsWorkerId(),
                repair.getEquipmentId(),
                repair.getRepairsWorkerId1()

        );
    }

    public static Repair mapDTOToRepair(RepairDTO repairDTO) {
        return new Repair(
                repairDTO.getId(),
                repairDTO.getTime(),
                repairDTO.getStartData(),
                repairDTO.getEndData(),
                repairDTO.getRepairsWorkerId(),
                repairDTO.getEquipmentId(),
                repairDTO.getRepairsWorkerId1()
        );
    }
}