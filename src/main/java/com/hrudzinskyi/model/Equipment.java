package com.hrudzinskyi.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "equipment", schema = "lab1db")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "inventory_numbers")
    private Integer inventoryNumbers;
    @Column(name = "color")
    private String color;
    @Column(name = "body_material")
    private String bodyMaterial;
    @Column(name = "condition_of_equipment")
    private String conditionOfEquipment;
}