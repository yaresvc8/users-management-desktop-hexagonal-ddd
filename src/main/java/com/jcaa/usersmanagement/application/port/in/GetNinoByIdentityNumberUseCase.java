package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.GetNinoByIdentityNumberQuery;
import com.jcaa.usersmanagement.domain.model.NinoModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface GetNinoByIdentityNumberUseCase {
  NinoModel execute(@NotNull @Valid GetNinoByIdentityNumberQuery query);
}
