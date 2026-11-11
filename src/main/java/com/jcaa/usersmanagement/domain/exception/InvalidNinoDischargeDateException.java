package com.jcaa.usersmanagement.domain.exception;

public final class InvalidNinoDischargeDateException extends DomainException {

    private static final String MESSAGE_EMPTY = "The child discharge date must not be empty.";
    private static final String MESSAGE_BEFORE_ADMISSION_DATE = "The discharge date cannot be before the admission date.";

    private InvalidNinoDischargeDateException(final String message) {
        super(message);
    }

    public static InvalidNinoDischargeDateException becauseValueIsEmpty() {
        return new InvalidNinoDischargeDateException(MESSAGE_EMPTY);
    }

    public static InvalidNinoDischargeDateException becauseValueIsBeforeAdmissionDate() {
        return new InvalidNinoDischargeDateException(MESSAGE_BEFORE_ADMISSION_DATE);
    }
}
