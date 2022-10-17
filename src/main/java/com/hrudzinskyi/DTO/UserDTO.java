package com.hrudzinskyi.DTO;

import lombok.*;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String surname;
    private String name;
    private String studentNumber;
    private Integer zip;
}