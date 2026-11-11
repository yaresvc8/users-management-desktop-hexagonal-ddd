package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeleteNinoPort;
import com.jcaa.usersmanagement.application.port.out.GetAllNinosPort;
import com.jcaa.usersmanagement.application.port.out.GetNinoByIdentityNumberPort;
import com.jcaa.usersmanagement.application.port.out.SaveNinoPort;
import com.jcaa.usersmanagement.application.port.out.UpdateNinoPort;
import com.jcaa.usersmanagement.domain.exception.NinoNotFoundException;
import com.jcaa.usersmanagement.domain.model.NinoModel;
import com.jcaa.usersmanagement.domain.valueobject.NinoIdentityNumber;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.NinoPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.NinoPersistenceMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public final class NinoRepositoryMySQL
    implements SaveNinoPort,
        UpdateNinoPort,
        GetNinoByIdentityNumberPort,
        GetAllNinosPort,
        DeleteNinoPort {

  private static final String SQL_INSERT =
      "INSERT INTO ninos "
          + "(identity_number, enrollment_number, name, birth_date, admission_date, discharge_date, created_at, updated_at) "
          + "VALUES (?, ?, ?, ?, ?, ?, NOW(), NOW())";

  private static final String SQL_UPDATE =
      "UPDATE ninos SET enrollment_number = ?, name = ?, birth_date = ?, admission_date = ?, discharge_date = ?, updated_at = NOW() "
          + "WHERE identity_number = ?";

  private static final String SQL_SELECT_BY_IDENTITY_NUMBER =
      "SELECT identity_number, enrollment_number, name, birth_date, admission_date, discharge_date, created_at, updated_at "
          + "FROM ninos WHERE identity_number = ? LIMIT 1";

  private static final String SQL_SELECT_ALL =
      "SELECT identity_number, enrollment_number, name, birth_date, admission_date, discharge_date, created_at, updated_at "
          + "FROM ninos ORDER BY name ASC";

  private static final String SQL_DELETE = "DELETE FROM ninos WHERE identity_number = ?";

  private final Connection connection;

  @Override
  public NinoModel save(final NinoModel nino) {
    final NinoPersistenceDto dto = NinoPersistenceMapper.fromModelToDto(nino);
    executeSave(dto);
    return findByIdentityNumberOrFail(nino.getIdentityNumber());
  }

  @Override
  public NinoModel update(final NinoModel nino) {
    final NinoPersistenceDto dto = NinoPersistenceMapper.fromModelToDto(nino);
    executeUpdate(dto);
    return findByIdentityNumberOrFail(nino.getIdentityNumber());
  }

  @Override
  public Optional<NinoModel> getByIdentityNumber(final NinoIdentityNumber identityNumber) {
    try (final PreparedStatement statement =
        connection.prepareStatement(SQL_SELECT_BY_IDENTITY_NUMBER)) {
      statement.setString(1, identityNumber.value());
      final ResultSet resultSet = statement.executeQuery();
      if (!resultSet.next()) {
        return Optional.empty();
      }
      return Optional.of(NinoPersistenceMapper.fromResultSetToModel(resultSet));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseNinoFindByIdentityNumberFailed(
          identityNumber.value(), exception);
    }
  }

  @Override
  public List<NinoModel> getAll() {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
      final ResultSet resultSet = statement.executeQuery();
      return NinoPersistenceMapper.fromResultSetToModelList(resultSet);
    } catch (final SQLException exception) {
      throw PersistenceException.becauseNinoFindAllFailed(exception);
    }
  }

  @Override
  public void delete(final NinoIdentityNumber identityNumber) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
      statement.setString(1, identityNumber.value());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseNinoDeleteFailed(identityNumber.value(), exception);
    }
  }

  private void executeSave(final NinoPersistenceDto dto) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
      statement.setString(1, dto.identityNumber());
      statement.setString(2, dto.enrollmentNumber());
      statement.setString(3, dto.name());
      statement.setDate(4, java.sql.Date.valueOf(dto.birthDate()));
      statement.setDate(5, java.sql.Date.valueOf(dto.admissionDate()));
      if (dto.dischargeDate() == null || dto.dischargeDate().isBlank()) {
        statement.setNull(6, java.sql.Types.DATE);
      } else {
        statement.setDate(6, java.sql.Date.valueOf(dto.dischargeDate()));
      }
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseNinoSaveFailed(dto.identityNumber(), exception);
    }
  }

  private void executeUpdate(final NinoPersistenceDto dto) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
      statement.setString(1, dto.enrollmentNumber());
      statement.setString(2, dto.name());
      statement.setDate(3, java.sql.Date.valueOf(dto.birthDate()));
      statement.setDate(4, java.sql.Date.valueOf(dto.admissionDate()));
      if (dto.dischargeDate() == null || dto.dischargeDate().isBlank()) {
        statement.setNull(5, java.sql.Types.DATE);
      } else {
        statement.setDate(5, java.sql.Date.valueOf(dto.dischargeDate()));
      }
      statement.setString(6, dto.identityNumber());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseNinoUpdateFailed(dto.identityNumber(), exception);
    }
  }

  private NinoModel findByIdentityNumberOrFail(final NinoIdentityNumber identityNumber) {
    return getByIdentityNumber(identityNumber)
        .orElseThrow(
            () -> NinoNotFoundException.becauseIdentityNumberWasNotFound(identityNumber.value()));
  }
}
