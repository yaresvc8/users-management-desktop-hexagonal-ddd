package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.exception.InvalidNinoAdmissionDateException;
import com.jcaa.usersmanagement.domain.exception.InvalidNinoDischargeDateException;
import com.jcaa.usersmanagement.domain.valueobject.NinoAdmissionDate;
import com.jcaa.usersmanagement.domain.valueobject.NinoBirthDate;
import com.jcaa.usersmanagement.domain.valueobject.NinoDischargeDate;
import com.jcaa.usersmanagement.domain.valueobject.NinoEnrollmentNumber;
import com.jcaa.usersmanagement.domain.valueobject.NinoIdentityNumber;
import com.jcaa.usersmanagement.domain.valueobject.NinoName;

import java.time.LocalDate;

import lombok.Value;

@Value
public class NinoModel {

    NinoIdentityNumber identityNumber;
    NinoEnrollmentNumber enrollmentNumber;
    NinoName name;
    NinoBirthDate birthDate;
    NinoAdmissionDate admissionDate;
    NinoDischargeDate dischargeDate;

    public static NinoModel create(
            final NinoIdentityNumber identityNumber,
            final NinoEnrollmentNumber enrollmentNumber,
            final NinoName name,
            final NinoBirthDate birthDate,
            final NinoAdmissionDate admissionDate,
            final NinoDischargeDate dischargeDate) {
        validateDates(birthDate.value(), admissionDate.value(), dischargeDate);
        return new NinoModel(identityNumber, enrollmentNumber, name, birthDate, admissionDate, dischargeDate);
    }

    public NinoModel discharge(final LocalDate dischargeDate) {
        final NinoDischargeDate nextDischargeDate = new NinoDischargeDate(dischargeDate);
        validateDischargeDate(admissionDate.value(), nextDischargeDate.value());
        return new NinoModel(
                identityNumber,
                enrollmentNumber,
                name,
                birthDate,
                admissionDate,
                nextDischargeDate);
    }

    private static void validateDates(
            final LocalDate birthDate,
            final LocalDate admissionDate,
            final NinoDischargeDate dischargeDate) {
        if (admissionDate.isBefore(birthDate)) {
            throw InvalidNinoAdmissionDateException.becauseValueIsBeforeBirthDate();
        }
        if (dischargeDate != null && dischargeDate.value().isBefore(admissionDate)) {
            throw InvalidNinoDischargeDateException.becauseValueIsBeforeAdmissionDate();
        }
    }

    private static void validateDischargeDate(
            final LocalDate admissionDate,
            final LocalDate dischargeDate) {
        if (dischargeDate.isBefore(admissionDate)) {
            throw InvalidNinoDischargeDateException.becauseValueIsBeforeAdmissionDate();
        }
    }
}
