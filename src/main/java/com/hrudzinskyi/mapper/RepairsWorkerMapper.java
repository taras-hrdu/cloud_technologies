package com.hrudzinskyi.mapper;

import com.hrudzinskyi.DTO.RepairsWorkerDTO;
import com.hrudzinskyi.model.RepairsWorker;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RepairsWorkerMapper {
    public static RepairsWorkerDTO mapRepairsWorkerToDTO(RepairsWorker repairsWorker) {
        return new RepairsWorkerDTO(
                repairsWorker.getId(),
                repairsWorker.getName(),
                repairsWorker.getSurname()
        );
    }

    public static RepairsWorker mapDTOToRepairsWorker(RepairsWorkerDTO repairsWorkerDTO) {
        return new RepairsWorker(
                repairsWorkerDTO.getId(),
                repairsWorkerDTO.getName(),
                repairsWorkerDTO.getSurname()
        );
    }
}