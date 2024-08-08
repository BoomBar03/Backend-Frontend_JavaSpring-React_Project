package com.example.movieticketstoremgmtbackend.service;
import com.example.movieticketstoremgmtbackend.dto.PageRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.UserRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.UserResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.movieticketstoremgmtbackend.dto.CollectionResponseDTO;
import com.example.movieticketstoremgmtbackend.model.UserEntity;

public interface UserService {

    UserResponseDTO findById(UUID chefId);

    List<UserResponseDTO> findAll();

    CollectionResponseDTO<UserResponseDTO> findAllPaged(PageRequestDTO page);

    List<UserResponseDTO> findAllSorted(String sortBy);

    UserResponseDTO save(UserRequestDTO userRequestDTO);

    UserResponseDTO findByEmail(String email);
}
