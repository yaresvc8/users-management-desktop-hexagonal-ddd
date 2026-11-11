package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllNinosUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllNinosPort;
import com.jcaa.usersmanagement.domain.model.NinoModel;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class GetAllNinosService implements GetAllNinosUseCase {

  private final GetAllNinosPort getAllNinosPort;

  @Override
  public List<NinoModel> execute() {
    return getAllNinosPort.getAll();
  }
}
