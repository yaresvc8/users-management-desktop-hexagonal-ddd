package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidNinoDischargeDateException;

import java.time.LocalDate;
import java.util.Objects;

public record NinoDischargeDate(LocalDate value) {

    public NinoDischargeDate {
        Objects.requireNonNull(value, "NinoDischargeDate cannot be null");
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
