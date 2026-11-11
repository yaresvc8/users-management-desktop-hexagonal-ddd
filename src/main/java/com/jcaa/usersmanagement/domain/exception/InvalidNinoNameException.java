package com.jcaa.usersmanagement.domain.exception;

public final class InvalidNinoNameException extends DomainException {

    private static final String MESSAGE_EMPTY = "The child name must not be empty.";
    private static final String MESSAGE_TOO_SHORT = "The child name must have at least %d characters.";

    private InvalidNinoNameException(final String message) {
        super(message);
    }

    public static InvalidNinoNameException becauseValueIsEmpty() {
        return new InvalidNinoNameException(MESSAGE_EMPTY);
    }

    public static InvalidNinoNameException becauseLengthIsTooShort(final int minimumLength) {
        return new InvalidNinoNameException(String.format(MESSAGE_TOO_SHORT, minimumLength));
    }
}
