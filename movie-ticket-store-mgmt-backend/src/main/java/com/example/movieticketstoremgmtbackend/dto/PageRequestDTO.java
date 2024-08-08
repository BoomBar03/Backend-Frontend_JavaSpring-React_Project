package com.example.movieticketstoremgmtbackend.dto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.movieticketstoremgmtbackend.validator.OddPageSize;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {

    @NotNull(message = "Page number is requested")
    private Integer pageNumber;
    @OddPageSize
    private Integer pageSize = 20;
}
