package com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception;

public final class PersistenceException extends RuntimeException {

  private static final String MESSAGE_SAVE = "Failed to save user with ID: '%s'.";
  private static final String MESSAGE_UPDATE = "Failed to update user with ID: '%s'.";
  private static final String MESSAGE_FIND = "Failed to find user with ID: '%s'.";
  private static final String MESSAGE_EMAIL = "Failed to find user with email: '%s'.";
  private static final String MESSAGE_ALL = "Failed to retrieve all users.";
  private static final String MESSAGE_DELETE = "Failed to delete user with ID: '%s'.";
  private static final String MESSAGE_CONNECTION = "Could not establish database connection.";

  private static final String MESSAGE_SAVE_NINO = "Failed to save child with identity number: '%s'.";
  private static final String MESSAGE_UPDATE_NINO = "Failed to update child with identity number: '%s'.";
  private static final String MESSAGE_FIND_NINO = "Failed to find child with identity number: '%s'.";
  private static final String MESSAGE_ALL_NINO = "Failed to retrieve all children.";
  private static final String MESSAGE_DELETE_NINO = "Failed to delete child with identity number: '%s'.";

  private PersistenceException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public static PersistenceException becauseSaveFailed(final String userId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_SAVE, userId), cause);
  }

  public static PersistenceException becauseUpdateFailed(
      final String userId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_UPDATE, userId), cause);
  }

  public static PersistenceException becauseFindByIdFailed(
      final String userId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_FIND, userId), cause);
  }

  public static PersistenceException becauseFindByEmailFailed(
      final String email, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_EMAIL, email), cause);
  }

  public static PersistenceException becauseFindAllFailed(final Throwable cause) {
    return new PersistenceException(MESSAGE_ALL, cause);
  }

  public static PersistenceException becauseDeleteFailed(
      final String userId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_DELETE, userId), cause);
  }

  public static PersistenceException becauseConnectionFailed(final Throwable cause) {
    return new PersistenceException(MESSAGE_CONNECTION, cause);
  }

  public static PersistenceException becauseNinoSaveFailed(
      final String identityNumber, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_SAVE_NINO, identityNumber), cause);
  }

  public static PersistenceException becauseNinoUpdateFailed(
      final String identityNumber, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_UPDATE_NINO, identityNumber), cause);
  }

  public static PersistenceException becauseNinoFindByIdentityNumberFailed(
      final String identityNumber, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_FIND_NINO, identityNumber), cause);
  }

  public static PersistenceException becauseNinoFindAllFailed(final Throwable cause) {
    return new PersistenceException(MESSAGE_ALL_NINO, cause);
  }

  public static PersistenceException becauseNinoDeleteFailed(
      final String identityNumber, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_DELETE_NINO, identityNumber), cause);
  }
}
