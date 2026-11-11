package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

import java.time.LocalDate;

public record CreateNinoRequest(
    String identityNumber,
    String enrollmentNumber,
    String name,
    LocalDate birthDate,
    LocalDate admissionDate,
    LocalDate dischargeDate) {}
