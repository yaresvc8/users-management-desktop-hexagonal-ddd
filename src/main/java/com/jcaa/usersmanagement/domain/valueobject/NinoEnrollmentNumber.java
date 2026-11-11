package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidNinoEnrollmentNumberException;

import java.util.Objects;

public record NinoEnrollmentNumber(String value) {

    public NinoEnrollmentNumber {
        final String normalizedValue = Objects.requireNonNull(value, "NinoEnrollmentNumber cannot be null").trim();
        validateNotEmpty(normalizedValue);
        validateDigits(normalizedValue);
        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidNinoEnrollmentNumberException.becauseValueIsEmpty();
        }
    }

    private static void validateDigits(final String normalizedValue) {
        if (!normalizedValue.matches("\\d+")) {
            throw InvalidNinoEnrollmentNumberException.becauseValueIsInvalid();
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
