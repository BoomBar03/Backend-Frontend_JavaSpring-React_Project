package com.example.movieticketstoremgmtbackend.mapper;

import com.example.movieticketstoremgmtbackend.dto.ReviewRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.ReviewResponseDTO;
import com.example.movieticketstoremgmtbackend.model.ReviewEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper interface for mapping between ReviewEntity and its corresponding DTOs.
 */
@Mapper
public interface ReviewMapper {

    /**
     * Maps a ReviewEntity to its corresponding ReviewResponseDTO.
     *
     * @param reviewEntity The ReviewEntity to map.
     * @return The mapped ReviewResponseDTO.
     */
    ReviewResponseDTO reviewEntityToReviewResponseDTO(ReviewEntity reviewEntity);

    /**
     * Maps a list of ReviewEntity objects to a list of ReviewResponseDTO objects.
     *
     * @param reviewEntityList The list of ReviewEntity objects to map.
     * @return The list of mapped ReviewResponseDTO objects.
     */
    List<ReviewResponseDTO> reviewEntityListToReviewResponseDTOList(List<ReviewEntity> reviewEntityList);

    /**
     * Maps a ReviewRequestDTO to its corresponding ReviewEntity.
     *
     * @param reviewRequestDTO The ReviewRequestDTO to map.
     * @return The mapped ReviewEntity.
     */
    ReviewEntity reviewRequestDTOToReviewEntity(ReviewRequestDTO reviewRequestDTO);
}
