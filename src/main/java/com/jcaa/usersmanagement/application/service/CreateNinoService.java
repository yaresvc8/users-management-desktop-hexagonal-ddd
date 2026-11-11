package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateNinoUseCase;
import com.jcaa.usersmanagement.application.port.out.GetNinoByIdentityNumberPort;
import com.jcaa.usersmanagement.application.port.out.SaveNinoPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateNinoCommand;
import com.jcaa.usersmanagement.application.service.mapper.NinoApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.NinoAlreadyExistsException;
import com.jcaa.usersmanagement.domain.model.NinoModel;
import com.jcaa.usersmanagement.domain.valueobject.NinoIdentityNumber;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateNinoService implements CreateNinoUseCase {

  private final SaveNinoPort saveNinoPort;
  private final GetNinoByIdentityNumberPort getNinoByIdentityNumberPort;
  private final Validator validator;

  @Override
  public NinoModel execute(final CreateNinoCommand command) {
    validateCommand(command);

    final NinoIdentityNumber identityNumber = new NinoIdentityNumber(command.identityNumber());
    ensureIdentityNumberIsNotTaken(identityNumber);

    final NinoModel ninoToSave = NinoApplicationMapper.fromCreateCommandToModel(command);
    return saveNinoPort.save(ninoToSave);
  }

  private void validateCommand(final CreateNinoCommand command) {
    final Set<ConstraintViolation<CreateNinoCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private void ensureIdentityNumberIsNotTaken(final NinoIdentityNumber identityNumber) {
    getNinoByIdentityNumberPort
        .getByIdentityNumber(identityNumber)
        .ifPresent(
            ignored -> {
              throw NinoAlreadyExistsException.becauseIdentityNumberAlreadyExists(
                  identityNumber.value());
            });
  }
}
