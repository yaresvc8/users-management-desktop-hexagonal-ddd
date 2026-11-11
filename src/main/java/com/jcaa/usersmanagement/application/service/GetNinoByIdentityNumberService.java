package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetNinoByIdentityNumberUseCase;
import com.jcaa.usersmanagement.application.port.out.GetNinoByIdentityNumberPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetNinoByIdentityNumberQuery;
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
public final class GetNinoByIdentityNumberService implements GetNinoByIdentityNumberUseCase {

  private final GetNinoByIdentityNumberPort getNinoByIdentityNumberPort;
  private final Validator validator;

  @Override
  public NinoModel execute(final GetNinoByIdentityNumberQuery query) {
    validateQuery(query);

    final NinoIdentityNumber identityNumber =
        NinoApplicationMapper.fromGetQueryToIdentityNumber(query);
    return getNinoByIdentityNumberPort
        .getByIdentityNumber(identityNumber)
        .orElseThrow(
            () -> NinoNotFoundException.becauseIdentityNumberWasNotFound(identityNumber.value()));
  }

  private void validateQuery(final GetNinoByIdentityNumberQuery query) {
    final Set<ConstraintViolation<GetNinoByIdentityNumberQuery>> violations =
        validator.validate(query);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
