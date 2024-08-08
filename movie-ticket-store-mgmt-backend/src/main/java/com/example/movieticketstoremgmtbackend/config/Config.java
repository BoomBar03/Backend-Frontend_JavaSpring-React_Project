package com.example.movieticketstoremgmtbackend.config;

import com.example.movieticketstoremgmtbackend.mapper.*;
import com.example.movieticketstoremgmtbackend.repository.*;
import com.example.movieticketstoremgmtbackend.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;

/**
 * Configuration class responsible for defining beans for various services in the application.
 */

@Configuration
public class Config {

    @Bean
    public UserService userServiceBean(
            UserRepository userRepository,
            UserMapper userMapper,
            @Value("${spring.application.name:BACKEND}") String applicationName
    ) {
        return new UserServiceBean(userRepository, userMapper, applicationName);
    }

    @Bean
    public MovieService movieServiceBean(
            MovieRepository movieRepository,
            MovieMapper movieMapper,
            @Value("${spring.application.name:BACKEND}") String applicationName
    ) {
        return new MovieServiceBean(movieRepository, movieMapper, applicationName);
    }


    @Bean
    public ReviewService reviewServiceBean(
            ReviewRepository reviewRepository,
            ReviewMapper reviewMapper,
            @Value("${spring.application.name:BACKEND}") String applicationName
    ) {
        return new ReviewServiceBean(reviewRepository, reviewMapper, applicationName);
    }


    @Bean
    public CinemaBarService cinemaBarServiceBean(
            CinemaBarRepository cinemaBarRepository,
            CinemaBarMapper cinemaBarMapper,
            @Value("${spring.application.name:BACKEND}") String applicationName
    ) {
        return new CinemaBarServiceBean(cinemaBarRepository, cinemaBarMapper, applicationName);
    }


    @Bean
    public SoldTicketService soldTicketServiceBean(
            SoldTicketRepository soldTicketRepository,
            SoldTicketMapper soldTicketMapper,
            @Value("${spring.application.name:BACKEND}") String applicationName
    ) {
        return new SoldTicketServiceBean(soldTicketRepository, soldTicketMapper, applicationName);
    }

}