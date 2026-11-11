package com.jcaa.usersmanagement.domain.valueobject;

import java.time.LocalDate;
import java.util.Objects;

public record NinoAdmissionDate(LocalDate value) {

    public NinoAdmissionDate {
        Objects.requireNonNull(value, "NinoAdmissionDate cannot be null");
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
