package com.hrudzinskyi.service;

import com.hrudzinskyi.DTO.UserDTO;
import com.hrudzinskyi.mapper.UserMapper;
import com.hrudzinskyi.model.User;
import com.hrudzinskyi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(UserDTO userDTO) {
        return userRepository.save(UserMapper.mapDTOToUser(userDTO));
    }

    public User updateUser(UserDTO userDTO) {
        if (getUserById(userDTO.getId()) != null) {
            return userRepository.save(UserMapper.mapDTOToUser(userDTO));
        }
        return null;
    }

    public User deleteUserById(Integer id) {
        User user = getUserById(id);
        if (user != null) {
            userRepository.deleteById(id);
            return user;
        }
        return null;
    }
}