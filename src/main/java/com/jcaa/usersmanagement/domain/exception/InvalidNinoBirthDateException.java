package com.jcaa.usersmanagement.domain.exception;

public final class InvalidNinoBirthDateException extends DomainException {

    private static final String MESSAGE_EMPTY = "The child birth date must not be empty.";
    private static final String MESSAGE_IN_FUTURE = "The child birth date cannot be in the future.";

    private InvalidNinoBirthDateException(final String message) {
        super(message);
    }

    public static InvalidNinoBirthDateException becauseValueIsEmpty() {
        return new InvalidNinoBirthDateException(MESSAGE_EMPTY);
    }

    public static InvalidNinoBirthDateException becauseValueIsInTheFuture() {
        return new InvalidNinoBirthDateException(MESSAGE_IN_FUTURE);
    }
}
