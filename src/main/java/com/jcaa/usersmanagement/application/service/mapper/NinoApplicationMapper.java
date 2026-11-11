package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateNinoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateNinoCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetNinoByIdentityNumberQuery;
import com.jcaa.usersmanagement.domain.model.NinoModel;
import com.jcaa.usersmanagement.domain.valueobject.NinoAdmissionDate;
import com.jcaa.usersmanagement.domain.valueobject.NinoBirthDate;
import com.jcaa.usersmanagement.domain.valueobject.NinoDischargeDate;
import com.jcaa.usersmanagement.domain.valueobject.NinoEnrollmentNumber;
import com.jcaa.usersmanagement.domain.valueobject.NinoIdentityNumber;
import com.jcaa.usersmanagement.domain.valueobject.NinoName;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NinoApplicationMapper {

  public NinoModel fromCreateCommandToModel(final CreateNinoCommand command) {
    return NinoModel.create(
        new NinoIdentityNumber(command.identityNumber()),
        new NinoEnrollmentNumber(command.enrollmentNumber()),
        new NinoName(command.name()),
        new NinoBirthDate(command.birthDate()),
        new NinoAdmissionDate(command.admissionDate()),
        toDischargeDate(command.dischargeDate()));
  }

  public NinoModel fromUpdateCommandToModel(final UpdateNinoCommand command) {
    return NinoModel.create(
        new NinoIdentityNumber(command.identityNumber()),
        new NinoEnrollmentNumber(command.enrollmentNumber()),
        new NinoName(command.name()),
        new NinoBirthDate(command.birthDate()),
        new NinoAdmissionDate(command.admissionDate()),
        toDischargeDate(command.dischargeDate()));
  }

  public NinoIdentityNumber fromGetQueryToIdentityNumber(
      final GetNinoByIdentityNumberQuery query) {
    return new NinoIdentityNumber(query.identityNumber());
  }

  private NinoDischargeDate toDischargeDate(final java.time.LocalDate date) {
    return date == null ? null : new NinoDischargeDate(date);
  }
}
