package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.NinoModel;
import java.util.List;

public interface GetAllNinosUseCase {
  List<NinoModel> execute();
}
