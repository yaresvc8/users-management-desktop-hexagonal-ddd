package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.model.NinoModel;

import java.util.Map;

import lombok.Getter;

@Getter
public final class NinoCreatedDomainEvent extends DomainEvent {

    private static final String EVENT_NAME = "nino.created";

    private final NinoModel nino;

    public NinoCreatedDomainEvent(final NinoModel nino) {
        super(EVENT_NAME);
        this.nino = nino;
    }

    @Override
    public Map<String, String> payload() {
        return Map.of(
                "identityNumber", nino.getIdentityNumber().value(),
                "enrollmentNumber", nino.getEnrollmentNumber().value(),
                "name", nino.getName().value(),
                "birthDate", nino.getBirthDate().value().toString(),
                "admissionDate", nino.getAdmissionDate().value().toString(),
                "dischargeDate",
                nino.getDischargeDate() == null ? "" : nino.getDischargeDate().value().toString());
    }
}
