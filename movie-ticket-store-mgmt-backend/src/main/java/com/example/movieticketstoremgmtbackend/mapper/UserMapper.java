package com.example.movieticketstoremgmtbackend.mapper;

import com.example.movieticketstoremgmtbackend.dto.UserResponseDTO;
import com.example.movieticketstoremgmtbackend.model.UserEntity;
import org.mapstruct.Mapper;
import com.example.movieticketstoremgmtbackend.dto.UserRequestDTO;


import java.util.List;

@Mapper
public interface UserMapper {

    UserResponseDTO userEntityToUserResponseDTO(UserEntity userEntity);

    List<UserResponseDTO> userEntityListToUserResponseDTOList(List<UserEntity> userEntityList);

    UserEntity userRequestDTOToUserEntity(UserRequestDTO userRequestDTO);
}
