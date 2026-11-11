package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.NinoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.NinoController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListNinosHandler implements OperationHandler {

  private final NinoController ninoController;
  private final NinoResponsePrinter printer;

  @Override
  public void handle() {
    printer.printList(ninoController.listAllNinos());
  }
}
