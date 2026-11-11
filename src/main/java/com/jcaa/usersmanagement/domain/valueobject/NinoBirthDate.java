package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidNinoBirthDateException;

import java.time.LocalDate;
import java.util.Objects;

public record NinoBirthDate(LocalDate value) {

    public NinoBirthDate {
        Objects.requireNonNull(value, "NinoBirthDate cannot be null");
        validateNotInFuture(value);
        value = value;
    }

    private static void validateNotInFuture(final LocalDate normalizedValue) {
        if (normalizedValue.isAfter(LocalDate.now())) {
            throw InvalidNinoBirthDateException.becauseValueIsInTheFuture();
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
