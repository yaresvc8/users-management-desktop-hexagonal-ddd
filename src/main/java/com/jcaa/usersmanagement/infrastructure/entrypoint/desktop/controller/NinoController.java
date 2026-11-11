package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateNinoUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteNinoUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllNinosUseCase;
import com.jcaa.usersmanagement.application.port.in.GetNinoByIdentityNumberUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateNinoUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateNinoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.NinoResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateNinoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.NinoDesktopMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class NinoController {

  private final CreateNinoUseCase createNinoUseCase;
  private final UpdateNinoUseCase updateNinoUseCase;
  private final DeleteNinoUseCase deleteNinoUseCase;
  private final GetNinoByIdentityNumberUseCase getNinoByIdentityNumberUseCase;
  private final GetAllNinosUseCase getAllNinosUseCase;

  public List<NinoResponse> listAllNinos() {
    return NinoDesktopMapper.toResponseList(getAllNinosUseCase.execute());
  }

  public NinoResponse findNinoByIdentityNumber(final String identityNumber) {
    final var query = NinoDesktopMapper.toGetByIdentityNumberQuery(identityNumber);
    return NinoDesktopMapper.toResponse(getNinoByIdentityNumberUseCase.execute(query));
  }

  public NinoResponse createNino(final CreateNinoRequest request) {
    final var command = NinoDesktopMapper.toCreateCommand(request);
    return NinoDesktopMapper.toResponse(createNinoUseCase.execute(command));
  }

  public NinoResponse updateNino(final UpdateNinoRequest request) {
    final var command = NinoDesktopMapper.toUpdateCommand(request);
    return NinoDesktopMapper.toResponse(updateNinoUseCase.execute(command));
  }

  public void deleteNino(final String identityNumber) {
    deleteNinoUseCase.execute(NinoDesktopMapper.toDeleteCommand(identityNumber));
  }
}
