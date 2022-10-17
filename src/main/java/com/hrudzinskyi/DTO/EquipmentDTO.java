package com.hrudzinskyi.DTO;

import lombok.*;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDTO {
    private Integer id;
    private Integer inventoryNumbers;
    private String color;
    private String bodyMaterial;
    private String conditionOfEquipment;
}