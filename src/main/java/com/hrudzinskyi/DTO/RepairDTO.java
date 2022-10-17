package com.hrudzinskyi.DTO;

import lombok.*;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RepairDTO {
    private Integer id;
    private String time;
    private String startData;
    private String endData;
    private Integer repairsWorkerId;
    private Integer equipmentId;
    private Integer repairsWorkerId1;
}