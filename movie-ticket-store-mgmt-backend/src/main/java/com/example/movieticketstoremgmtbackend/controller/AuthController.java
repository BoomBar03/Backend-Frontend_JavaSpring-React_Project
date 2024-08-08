package com.example.movieticketstoremgmtbackend.controller;

import com.example.movieticketstoremgmtbackend.dto.UserRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.UserResponseDTO;
import com.example.movieticketstoremgmtbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/auth/v1")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Logs in the user.")
    @ApiResponse(responseCode = "200", description = "Login successful")
    public ResponseEntity<Void> login() {
        log.info("Login request detected...");

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    @Operation(summary = "User registration", description = "Registers a new user.")
    @ApiResponse(responseCode = "201", description = "User registered successfully",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))})
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO userRequestDTO) {
        log.info("Registration request detected...");

        return new ResponseEntity<>(
                userService.save(userRequestDTO),
                HttpStatus.CREATED
        );
    }
}