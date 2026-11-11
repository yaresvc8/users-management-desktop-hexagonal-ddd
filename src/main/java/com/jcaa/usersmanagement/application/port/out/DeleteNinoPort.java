package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.valueobject.NinoIdentityNumber;

public interface DeleteNinoPort {
  void delete(NinoIdentityNumber identityNumber);
}
