package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.NinoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.NinoController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindNinoByIdentityNumberHandler implements OperationHandler {

  private final NinoController ninoController;
  private final ConsoleIO console;
  private final NinoResponsePrinter printer;

  @Override
  public void handle() {
    final String identityNumber = console.readRequired("Identity number: ");
    printer.print(ninoController.findNinoByIdentityNumber(identityNumber));
  }
}
