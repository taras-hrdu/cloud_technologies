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
@Table(name = "repair", schema = "lab1db")
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "time")
    private String time;
    @Column(name = "start_data")
    private String startData;
    @Column(name = "end_data")
    private String endData;
    @Column(name = "repairs_worker_id")
    private Integer repairsWorkerId;
    @Column(name = "equipment_id")
    private Integer equipmentId;
    @Column(name = "repairs_worker_id1")
    private Integer repairsWorkerId1;

}