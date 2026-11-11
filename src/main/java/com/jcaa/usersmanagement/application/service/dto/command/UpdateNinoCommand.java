package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record UpdateNinoCommand(
    @NotBlank(message = "identityNumber must not be blank") String identityNumber,
    @NotBlank(message = "enrollmentNumber must not be blank") String enrollmentNumber,
    @NotBlank(message = "name must not be blank") String name,
    @NotNull(message = "birthDate must not be null") LocalDate birthDate,
    @NotNull(message = "admissionDate must not be null") LocalDate admissionDate,
    LocalDate dischargeDate) {
}
