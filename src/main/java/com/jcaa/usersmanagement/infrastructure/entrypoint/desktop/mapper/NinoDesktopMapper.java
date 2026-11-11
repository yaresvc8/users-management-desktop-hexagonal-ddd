package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateNinoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteNinoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateNinoCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetNinoByIdentityNumberQuery;
import com.jcaa.usersmanagement.domain.model.NinoModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateNinoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.NinoResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateNinoRequest;
import java.util.List;

public final class NinoDesktopMapper {

  private NinoDesktopMapper() {}

  public static CreateNinoCommand toCreateCommand(final CreateNinoRequest request) {
    return new CreateNinoCommand(
        request.identityNumber(),
        request.enrollmentNumber(),
        request.name(),
        request.birthDate(),
        request.admissionDate(),
        request.dischargeDate());
  }

  public static UpdateNinoCommand toUpdateCommand(final UpdateNinoRequest request) {
    return new UpdateNinoCommand(
        request.identityNumber(),
        request.enrollmentNumber(),
        request.name(),
        request.birthDate(),
        request.admissionDate(),
        request.dischargeDate());
  }

  public static DeleteNinoCommand toDeleteCommand(final String identityNumber) {
    return new DeleteNinoCommand(identityNumber);
  }

  public static GetNinoByIdentityNumberQuery toGetByIdentityNumberQuery(
      final String identityNumber) {
    return new GetNinoByIdentityNumberQuery(identityNumber);
  }

  public static NinoResponse toResponse(final NinoModel nino) {
    return new NinoResponse(
        nino.getIdentityNumber().value(),
        nino.getEnrollmentNumber().value(),
        nino.getName().value(),
        nino.getBirthDate().value(),
        nino.getAdmissionDate().value(),
        nino.getDischargeDate() == null ? null : nino.getDischargeDate().value());
  }

  public static List<NinoResponse> toResponseList(final List<NinoModel> ninos) {
    return ninos.stream().map(NinoDesktopMapper::toResponse).toList();
  }
}
