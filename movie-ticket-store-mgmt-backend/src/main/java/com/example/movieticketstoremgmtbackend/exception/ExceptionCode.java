package com.example.movieticketstoremgmtbackend.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum representing various exception codes along with corresponding error messages.
 * Each enum constant includes a message template that can be formatted with additional information.
 */
@Getter
@RequiredArgsConstructor
public enum ExceptionCode {
    ERR001_USER_NOT_FOUND("User with ID %s not found"),

    ERR002_MOVIE_NOT_FOUND("Movie with ID %s not found"),

    ERR003_REVIEW_NOT_FOUND("Review with ID %s not found"),

    ERR004_TICKET_NOT_FOUND("Sold Ticket with ID %s not found"),

    ERR_TICKET_MUST_BE_POSITIVE_NUMBER("Ticket price must be a positive number!"),

    ERR_NO_MOVIES_FOUND("No movies were found!"),

    ERR_INVALID_MOVIE("Movie to be added is invalid!"),

    ERR_NO_REVIEWS_FOUND("No reviews were found!"),

    ERR_INVALID_REVIEW("Review to be added is invalid!"),

    ERR_NO_SOLD_TICKETS_FOUND("No Sold Tickets were found!"),

    ERR_INVALID_SOLD_TICKET("The provided sold ticket is invalid!"),

    ERR_INVALID_ID("The provided id is invalid!"),

    ERR_NO_USERS_FOUND("No users were found!"),

    ERR_INVALID_USER("User to be added is invalid!"),

    ERR_RATING_MUST_BE_POSITIVE_NUMBER("Invalid rating! Rating must be a positive number"),

    ERR099_INVALID_CREDENTIALS("Invalid credentials."),

    ERR_NO_CINEMABAR_MENU_FOUND("No menus were found!"),

    ERR_INVALID_CINEMABAR_MENU("Menu to be added is invalid."),

    ERR_CINEMABAR_MENU_NOT_FOUND("No Cinema Bar Menu was found with ID %s");

    private final String message;
}