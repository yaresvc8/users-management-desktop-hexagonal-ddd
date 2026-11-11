package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.NinoModel;

public interface UpdateNinoPort {
  NinoModel update(NinoModel nino);
}
