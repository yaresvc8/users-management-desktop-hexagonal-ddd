package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.NinoResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class NinoResponsePrinter {

  private static final String SEPARATOR = "-".repeat(52);
  private static final String ROW_FORMAT = "  %-18s : %s%n";

  private final ConsoleIO console;

  public void print(final NinoResponse nino) {
    console.println(SEPARATOR);
    console.printf(ROW_FORMAT, "Identity number", nino.identityNumber());
    console.printf(ROW_FORMAT, "Enrollment number", nino.enrollmentNumber());
    console.printf(ROW_FORMAT, "Name", nino.name());
    console.printf(ROW_FORMAT, "Birth date", nino.birthDate());
    console.printf(ROW_FORMAT, "Admission date", nino.admissionDate());
    console.printf(ROW_FORMAT, "Discharge date", nino.dischargeDate() == null ? "N/A" : nino.dischargeDate());
    console.println(SEPARATOR);
  }

  public void printList(final List<NinoResponse> ninos) {
    if (ninos.isEmpty()) {
      console.println("  No children found.");
      return;
    }
    console.printf("%n  Total: %d child(ren)%n", ninos.size());
    ninos.forEach(this::print);
  }
}
