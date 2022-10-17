package com.hrudzinskyi.DTO;

import lombok.*;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentTransferDTO {
    private Integer id;
    private String time;
    private String startData;
    private String endData;
}