package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.NinoModel;

public interface SaveNinoPort {
  NinoModel save(NinoModel nino);
}
