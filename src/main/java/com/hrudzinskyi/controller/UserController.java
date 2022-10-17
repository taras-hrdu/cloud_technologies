package com.hrudzinskyi.controller;

import com.hrudzinskyi.DTO.UserDTO;
import com.hrudzinskyi.mapper.UserMapper;
import com.hrudzinskyi.model.User;
import com.hrudzinskyi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDTO> getAllEquipment() {
        return userService.getAllUser().stream()
                .map(UserMapper::mapUserToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                UserMapper.mapUserToDTO(user), HttpStatus.OK);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return UserMapper.
                mapUserToDTO(userService.createUser(userDTO));
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        User user = userService.
                getUserById(userDTO.getId());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                UserMapper.
                        mapUserToDTO(userService.
                                updateUser(userDTO)), HttpStatus.OK
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                UserMapper.mapUserToDTO(userService.
                        deleteUserById(id)), HttpStatus.OK
        );
    }

}