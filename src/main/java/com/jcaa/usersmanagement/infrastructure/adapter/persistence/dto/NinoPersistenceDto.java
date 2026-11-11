package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record NinoPersistenceDto(
    String identityNumber,
    String enrollmentNumber,
    String name,
    String birthDate,
    String admissionDate,
    String dischargeDate,
    String createdAt,
    String updatedAt) {}
