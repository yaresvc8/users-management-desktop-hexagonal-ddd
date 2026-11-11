package com.jcaa.usersmanagement.domain.exception;

public final class InvalidNinoIdentityNumberException extends DomainException {

    private static final String MESSAGE_EMPTY = "The child identity number must not be empty.";
    private static final String MESSAGE_INVALID = "The child identity number must contain only digits.";

    private InvalidNinoIdentityNumberException(final String message) {
        super(message);
    }

    public static InvalidNinoIdentityNumberException becauseValueIsEmpty() {
        return new InvalidNinoIdentityNumberException(MESSAGE_EMPTY);
    }

    public static InvalidNinoIdentityNumberException becauseValueIsInvalid() {
        return new InvalidNinoIdentityNumberException(MESSAGE_INVALID);
    }
}
