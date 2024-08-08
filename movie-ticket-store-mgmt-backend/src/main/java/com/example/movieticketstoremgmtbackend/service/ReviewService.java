package com.example.movieticketstoremgmtbackend.service;

import com.example.movieticketstoremgmtbackend.dto.CollectionResponseDTO;
import com.example.movieticketstoremgmtbackend.dto.PageRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.ReviewRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.ReviewResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    ReviewResponseDTO findById(UUID reviewId);

    List<ReviewResponseDTO> findAll();

    CollectionResponseDTO<ReviewResponseDTO> findAllPaged(PageRequestDTO page);

    List<ReviewResponseDTO> findAllSorted(String sortBy);

    List<ReviewResponseDTO> findAllByUserId(UUID userId);

    List<ReviewResponseDTO> findAllByMovieId(UUID movieId);
    List<ReviewResponseDTO> findAllByRatingGreaterThan(double rating);

    ReviewResponseDTO save(ReviewRequestDTO reviewRequestDTO);
}
