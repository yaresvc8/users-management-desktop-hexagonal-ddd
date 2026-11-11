package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteNinoUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteNinoPort;
import com.jcaa.usersmanagement.application.port.out.GetNinoByIdentityNumberPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteNinoCommand;
import com.jcaa.usersmanagement.application.service.mapper.NinoApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.NinoNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.NinoIdentityNumber;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteNinoService implements DeleteNinoUseCase {

  private final DeleteNinoPort deleteNinoPort;
  private final GetNinoByIdentityNumberPort getNinoByIdentityNumberPort;
  private final Validator validator;

  @Override
  public void execute(final DeleteNinoCommand command) {
    validateCommand(command);

    final NinoIdentityNumber identityNumber =
        new NinoIdentityNumber(command.identityNumber());
    getNinoByIdentityNumberPort
        .getByIdentityNumber(identityNumber)
        .orElseThrow(
            () -> NinoNotFoundException.becauseIdentityNumberWasNotFound(identityNumber.value()));

    deleteNinoPort.delete(identityNumber);
  }

  private void validateCommand(final DeleteNinoCommand command) {
    final Set<ConstraintViolation<DeleteNinoCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
