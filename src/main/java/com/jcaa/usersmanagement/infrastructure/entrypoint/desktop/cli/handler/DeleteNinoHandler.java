package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.NinoController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteNinoHandler implements OperationHandler {

  private final NinoController ninoController;
  private final ConsoleIO console;

  @Override
  public void handle() {
    final String identityNumber = console.readRequired("Identity number: ");
    ninoController.deleteNino(identityNumber);
    console.println("  Child deleted successfully.");
  }
}
