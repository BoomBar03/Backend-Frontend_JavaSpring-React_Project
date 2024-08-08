package com.example.movieticketstoremgmtbackend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.movieticketstoremgmtbackend.exception.ExceptionCode;
import com.example.movieticketstoremgmtbackend.exception.NotFoundException;
import com.example.movieticketstoremgmtbackend.mapper.UserMapper;
import com.example.movieticketstoremgmtbackend.dto.UserRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.UserResponseDTO;
import com.example.movieticketstoremgmtbackend.dto.PageRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.movieticketstoremgmtbackend.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import com.example.movieticketstoremgmtbackend.repository.UserRepository;
import com.example.movieticketstoremgmtbackend.dto.CollectionResponseDTO;

/**
 * Implementation of the {@link UserService} interface.
 */
@Slf4j
@RequiredArgsConstructor
public class UserServiceBean implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final String applicationName;

    /**
     * Retrieves a user by their ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The {@link UserResponseDTO} representing the user.
     * @throws NotFoundException if the user with the specified ID is not found.
     */
    @Override
    public UserResponseDTO findById(UUID userId) {
        return userRepository.findById(userId)
                .map(userMapper::userEntityToUserResponseDTO)
                .orElseThrow(() -> new NotFoundException(String.format(
                        ExceptionCode.ERR001_USER_NOT_FOUND.getMessage(),
                        userId
                )));
    }

    public UserResponseDTO findByEmail(String email){
        return userRepository.findByEmail(email)
                .map(userMapper::userEntityToUserResponseDTO)
                .orElseThrow(() -> new NotFoundException(String.format(
                        ExceptionCode.ERR001_USER_NOT_FOUND.getMessage(),
                        email
                )));
    }

    /**
     * Retrieves all users.
     *
     * @return A list of {@link UserResponseDTO} representing all users.
     * @throws NotFoundException if no users are found.
     */
    @Override
    public List<UserResponseDTO> findAll() {
        log.info("Getting all users for application {}", applicationName);

        List<UserEntity> userEntityList = userRepository.findAll();
        if (userEntityList.isEmpty()) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_NO_USERS_FOUND.getMessage()
            ));
        }
        return userMapper.userEntityListToUserResponseDTOList(userEntityList);
    }

    /**
     * Retrieves all users in a paged manner.
     *
     * @param page The pagination information.
     * @return A {@link CollectionResponseDTO} containing a list of {@link UserResponseDTO} and the total count.
     * @throws NotFoundException if no users are found.
     */
    @Override
    public CollectionResponseDTO<UserResponseDTO> findAllPaged(PageRequestDTO page) {
        Page<UserEntity> userEntityList = userRepository.findAll(PageRequest.of(
                page.getPageNumber(),
                page.getPageSize()
        ));
        List<UserResponseDTO> users = userMapper.userEntityListToUserResponseDTOList(userEntityList.getContent());
        if (users.isEmpty()) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_NO_USERS_FOUND.getMessage()
            ));
        }
        return new CollectionResponseDTO<>(users, userEntityList.getTotalElements());
    }

    /**
     * Retrieves all users sorted by a specified field.
     *
     * @param sortBy The field to sort by.
     * @return A list of {@link UserResponseDTO} representing all users sorted by the specified field.
     * @throws NotFoundException if no users are found.
     */
    @Override
    public List<UserResponseDTO> findAllSorted(String sortBy) {
        List<UserEntity> userEntityList = userRepository.findAll(Sort.by(sortBy).descending());
        if (userEntityList.isEmpty()) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_NO_USERS_FOUND.getMessage()
            ));
        }
        return userMapper.userEntityListToUserResponseDTOList(userEntityList);
    }

    /**
     * Saves a new user.
     *
     * @param userRequestDTO The DTO representing the user to be saved.
     * @return The {@link UserResponseDTO} representing the saved user.
     * @throws NotFoundException if the user is invalid.
     */
    @Override
    @Transactional
    public UserResponseDTO save(UserRequestDTO userRequestDTO) {
        UserEntity userToBeAdded = userMapper.userRequestDTOToUserEntity(userRequestDTO);

        if (userToBeAdded.getId() == null &&
                userToBeAdded.getName() == null &&
                userToBeAdded.getEmail() == null &&
                userToBeAdded.getPassword() == null) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_INVALID_USER.getMessage()
            ));
        }

        UserEntity userAdded = userRepository.save(userToBeAdded);

        return userMapper.userEntityToUserResponseDTO(userAdded);
    }
}
