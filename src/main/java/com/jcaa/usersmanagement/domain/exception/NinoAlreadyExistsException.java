package com.jcaa.usersmanagement.domain.exception;

public final class NinoAlreadyExistsException extends DomainException {

  private static final String MESSAGE = "The child with identity number %s already exists.";

  private NinoAlreadyExistsException(final String message) {
    super(message);
  }

  public static NinoAlreadyExistsException becauseIdentityNumberAlreadyExists(
      final String identityNumber) {
    return new NinoAlreadyExistsException(String.format(MESSAGE, identityNumber));
  }
}
