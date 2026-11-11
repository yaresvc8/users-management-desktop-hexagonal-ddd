package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.NinoModel;
import com.jcaa.usersmanagement.domain.valueobject.NinoAdmissionDate;
import com.jcaa.usersmanagement.domain.valueobject.NinoBirthDate;
import com.jcaa.usersmanagement.domain.valueobject.NinoDischargeDate;
import com.jcaa.usersmanagement.domain.valueobject.NinoEnrollmentNumber;
import com.jcaa.usersmanagement.domain.valueobject.NinoIdentityNumber;
import com.jcaa.usersmanagement.domain.valueobject.NinoName;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.NinoPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.NinoEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NinoPersistenceMapper {

  public NinoPersistenceDto fromModelToDto(final NinoModel nino) {
    return new NinoPersistenceDto(
        nino.getIdentityNumber().value(),
        nino.getEnrollmentNumber().value(),
        nino.getName().value(),
        nino.getBirthDate().value().toString(),
        nino.getAdmissionDate().value().toString(),
        nino.getDischargeDate() == null ? null : nino.getDischargeDate().value().toString(),
        null,
        null);
  }

  public NinoEntity fromResultSetToEntity(final ResultSet resultSet) throws SQLException {
    final java.sql.Date birthDate = resultSet.getDate("birth_date");
    final java.sql.Date admissionDate = resultSet.getDate("admission_date");
    final java.sql.Date dischargeDate = resultSet.getDate("discharge_date");

    return new NinoEntity(
        resultSet.getString("identity_number"),
        resultSet.getString("enrollment_number"),
        resultSet.getString("name"),
        birthDate == null ? null : birthDate.toLocalDate().toString(),
        admissionDate == null ? null : admissionDate.toLocalDate().toString(),
        dischargeDate == null ? null : dischargeDate.toLocalDate().toString(),
        resultSet.getString("created_at"),
        resultSet.getString("updated_at"));
  }

  public NinoModel fromEntityToModel(final NinoEntity entity) {
    return NinoModel.create(
        new NinoIdentityNumber(entity.identityNumber()),
        new NinoEnrollmentNumber(entity.enrollmentNumber()),
        new NinoName(entity.name()),
        new NinoBirthDate(LocalDate.parse(entity.birthDate())),
        new NinoAdmissionDate(LocalDate.parse(entity.admissionDate())),
        entity.dischargeDate() == null ? null : new NinoDischargeDate(LocalDate.parse(entity.dischargeDate())));
  }

  public NinoModel fromResultSetToModel(final ResultSet resultSet) throws SQLException {
    return fromEntityToModel(fromResultSetToEntity(resultSet));
  }

  public List<NinoModel> fromResultSetToModelList(final ResultSet resultSet) throws SQLException {
    final List<NinoModel> ninos = new ArrayList<>();
    while (resultSet.next()) {
      ninos.add(fromResultSetToModel(resultSet));
    }
    return ninos;
  }
}
