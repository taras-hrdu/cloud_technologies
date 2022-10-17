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
@Table(name = "laser_cutter", schema = "lab1db")
public class LaserCutter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "time_using")
    private String timeUsing;
    @Column(name = "start_data")
    private String startData;
    @Column(name = "end_data")
    private String endData;
    @Column(name = "equipment_id")
    private Integer equipmentId;
}