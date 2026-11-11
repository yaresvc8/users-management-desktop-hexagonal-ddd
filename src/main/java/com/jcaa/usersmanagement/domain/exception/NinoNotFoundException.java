package com.jcaa.usersmanagement.domain.exception;

public final class NinoNotFoundException extends DomainException {

  private static final String MESSAGE = "The child with identity number %s was not found.";

  private NinoNotFoundException(final String message) {
    super(message);
  }

  public static NinoNotFoundException becauseIdentityNumberWasNotFound(final String identityNumber) {
    return new NinoNotFoundException(String.format(MESSAGE, identityNumber));
  }
}
