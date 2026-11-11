package com.jcaa.usersmanagement.application.service.dto.query;

import jakarta.validation.constraints.NotBlank;

public record GetNinoByIdentityNumberQuery(
    @NotBlank(message = "identityNumber must not be blank") String identityNumber) {
}
