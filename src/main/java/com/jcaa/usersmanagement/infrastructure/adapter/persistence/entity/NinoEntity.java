package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

public record NinoEntity(
    String identityNumber,
    String enrollmentNumber,
    String name,
    String birthDate,
    String admissionDate,
    String dischargeDate,
    String createdAt,
    String updatedAt) {}
