package com.example.movieticketstoremgmtbackend.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.movieticketstoremgmtbackend.dto.CollectionResponseDTO;
import com.example.movieticketstoremgmtbackend.dto.PageRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.UserRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.UserResponseDTO;
import com.example.movieticketstoremgmtbackend.exception.ExceptionBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.movieticketstoremgmtbackend.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/user/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @Operation(summary = "Gets user by ID", description = "User must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<UserResponseDTO> findById(@PathVariable("id") UUID userId) {
        return new ResponseEntity<>(
                userService.findById(userId),
                HttpStatus.OK
        );
    }

    @GetMapping("/all")
    @Operation(summary = "Get all users", description = "Retrieves all users.")
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return new ResponseEntity<>(
                userService.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{email}")
    @Operation(summary = "Gets user by Email", description = "User must exist")
    public ResponseEntity<UserResponseDTO>findByEmail(@PathVariable("email") String email){
        return new ResponseEntity<>(
                userService.findByEmail(email),
                HttpStatus.OK
        );
    }


    @GetMapping("/all-paged")
    @Operation(summary = "Get users with pagination", description = "Retrieves users with pagination.")
    public ResponseEntity<CollectionResponseDTO<UserResponseDTO>> findAllPaged(@Valid PageRequestDTO page) {
        return new ResponseEntity<>(
                userService.findAllPaged(page),
                HttpStatus.OK
        );
    }


    @GetMapping("/all-sorted")
    @Operation(summary = "Get all users sorted", description = "Retrieves all users sorted by a specific field.")
    public ResponseEntity<List<UserResponseDTO>> findAllSorted(
            @RequestParam(value = "sortBy", defaultValue = "", required = false) String sortBy
    ) {
        return new ResponseEntity<>(
                userService.findAllSorted(sortBy),
                HttpStatus.OK
        );
    }


    @PostMapping("/save")
    @Operation(summary = "Save a new user", description = "Creates a new user.")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> save(
            @RequestBody UserRequestDTO userRequestDTO
    ) {
        return new ResponseEntity<>(
                userService.save(userRequestDTO),
                HttpStatus.CREATED
        );
    }
}

