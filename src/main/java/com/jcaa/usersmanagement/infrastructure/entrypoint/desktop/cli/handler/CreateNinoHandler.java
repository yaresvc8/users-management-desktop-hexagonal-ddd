package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.NinoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.NinoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateNinoRequest;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateNinoHandler implements OperationHandler {

  private final NinoController ninoController;
  private final ConsoleIO console;
  private final NinoResponsePrinter printer;

  @Override
  public void handle() {
    final String identityNumber = console.readRequired("Identity number : ");
    final String enrollmentNumber = console.readRequired("Enrollment number: ");
    final String name = console.readRequired("Name            : ");
    final LocalDate birthDate = readDate("Birth date (YYYY-MM-DD)   : ");
    final LocalDate admissionDate = readDate("Admission date (YYYY-MM-DD): ");
    final LocalDate dischargeDate = readOptionalDate("Discharge date (YYYY-MM-DD, blank if active): ");

    final var created =
        ninoController.createNino(
            new CreateNinoRequest(
                identityNumber, enrollmentNumber, name, birthDate, admissionDate, dischargeDate));
    console.println("\n  Child created successfully.");
    printer.print(created);
  }

  private LocalDate readDate(final String prompt) {
    return LocalDate.parse(console.readRequired(prompt));
  }

  private LocalDate readOptionalDate(final String prompt) {
    final String date = console.readOptional(prompt);
    return date.isBlank() ? null : LocalDate.parse(date);
  }
}
