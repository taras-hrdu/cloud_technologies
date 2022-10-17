package com.hrudzinskyi.mapper;

import com.hrudzinskyi.DTO.UserDTO;
import com.hrudzinskyi.model.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserMapper {
    public static UserDTO mapUserToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getSurname(),
                user.getName(),
                user.getStudentNumber(),
                user.getZip()
        );
    }

    public static User mapDTOToUser(UserDTO userDTO) {
        return new User(
                userDTO.getId(),
                userDTO.getSurname(),
                userDTO.getName(),
                userDTO.getStudentNumber(),
                userDTO.getZip()
        );
    }
}