package com.hrudzinskyi.DTO;

import lombok.*;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LaserCutterDTO {
    private Integer id;
    private String timeUsing;
    private String startData;
    private String endData;
    private Integer equipmentId;
}