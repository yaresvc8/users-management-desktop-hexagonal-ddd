package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateNinoUseCase;
import com.jcaa.usersmanagement.application.port.out.GetNinoByIdentityNumberPort;
import com.jcaa.usersmanagement.application.port.out.UpdateNinoPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateNinoCommand;
import com.jcaa.usersmanagement.application.service.mapper.NinoApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.NinoNotFoundException;
import com.jcaa.usersmanagement.domain.model.NinoModel;
import com.jcaa.usersmanagement.domain.valueobject.NinoIdentityNumber;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateNinoService implements UpdateNinoUseCase {

  private final UpdateNinoPort updateNinoPort;
  private final GetNinoByIdentityNumberPort getNinoByIdentityNumberPort;
  private final Validator validator;

  @Override
  public NinoModel execute(final UpdateNinoCommand command) {
    validateCommand(command);

    final NinoIdentityNumber identityNumber = new NinoIdentityNumber(command.identityNumber());
    final NinoModel current =
        getNinoByIdentityNumberPort
            .getByIdentityNumber(identityNumber)
            .orElseThrow(
                () -> NinoNotFoundException.becauseIdentityNumberWasNotFound(identityNumber.value()));

    final NinoModel ninoToUpdate = NinoApplicationMapper.fromUpdateCommandToModel(command);
    return updateNinoPort.update(ninoToUpdate);
  }

  private void validateCommand(final UpdateNinoCommand command) {
    final Set<ConstraintViolation<UpdateNinoCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
