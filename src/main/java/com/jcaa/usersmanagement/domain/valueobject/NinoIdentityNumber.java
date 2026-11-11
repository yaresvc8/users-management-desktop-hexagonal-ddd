package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidNinoIdentityNumberException;

import java.util.Objects;

public record NinoIdentityNumber(String value) {

    public NinoIdentityNumber {
        final String normalizedValue = Objects.requireNonNull(value, "NinoIdentityNumber cannot be null").trim();
        validateNotEmpty(normalizedValue);
        validateDigits(normalizedValue);
        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidNinoIdentityNumberException.becauseValueIsEmpty();
        }
    }

    private static void validateDigits(final String normalizedValue) {
        if (!normalizedValue.matches("\\d+")) {
            throw InvalidNinoIdentityNumberException.becauseValueIsInvalid();
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
