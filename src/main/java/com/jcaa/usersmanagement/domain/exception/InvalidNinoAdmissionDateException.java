package com.jcaa.usersmanagement.domain.exception;

public final class InvalidNinoAdmissionDateException extends DomainException {

    private static final String MESSAGE_EMPTY = "The child admission date must not be empty.";
    private static final String MESSAGE_BEFORE_BIRTH_DATE = "The admission date cannot be before the birth date.";

    private InvalidNinoAdmissionDateException(final String message) {
        super(message);
    }

    public static InvalidNinoAdmissionDateException becauseValueIsEmpty() {
        return new InvalidNinoAdmissionDateException(MESSAGE_EMPTY);
    }

    public static InvalidNinoAdmissionDateException becauseValueIsBeforeBirthDate() {
        return new InvalidNinoAdmissionDateException(MESSAGE_BEFORE_BIRTH_DATE);
    }
}
