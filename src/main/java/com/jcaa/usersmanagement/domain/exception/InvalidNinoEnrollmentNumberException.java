package com.jcaa.usersmanagement.domain.exception;

public final class InvalidNinoEnrollmentNumberException extends DomainException {

    private static final String MESSAGE_EMPTY = "The child enrollment number must not be empty.";
    private static final String MESSAGE_INVALID = "The child enrollment number must contain only digits.";

    private InvalidNinoEnrollmentNumberException(final String message) {
        super(message);
    }

    public static InvalidNinoEnrollmentNumberException becauseValueIsEmpty() {
        return new InvalidNinoEnrollmentNumberException(MESSAGE_EMPTY);
    }

    public static InvalidNinoEnrollmentNumberException becauseValueIsInvalid() {
        return new InvalidNinoEnrollmentNumberException(MESSAGE_INVALID);
    }
}
