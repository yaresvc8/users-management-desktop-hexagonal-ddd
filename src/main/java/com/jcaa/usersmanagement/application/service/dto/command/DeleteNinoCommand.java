package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;

public record DeleteNinoCommand(@NotBlank(message = "identityNumber must not be blank") String identityNumber) {
}
