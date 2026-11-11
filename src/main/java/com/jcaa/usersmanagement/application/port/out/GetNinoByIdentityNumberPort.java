package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.NinoModel;
import com.jcaa.usersmanagement.domain.valueobject.NinoIdentityNumber;
import java.util.Optional;

public interface GetNinoByIdentityNumberPort {
  Optional<NinoModel> getByIdentityNumber(NinoIdentityNumber identityNumber);
}
