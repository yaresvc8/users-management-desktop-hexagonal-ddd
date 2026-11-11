package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.NinoModel;
import java.util.List;

public interface GetAllNinosPort {
  List<NinoModel> getAll();
}
