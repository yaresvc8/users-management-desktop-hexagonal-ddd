package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.UpdateNinoCommand;
import com.jcaa.usersmanagement.domain.model.NinoModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UpdateNinoUseCase {
  NinoModel execute(@NotNull @Valid UpdateNinoCommand command);
}
