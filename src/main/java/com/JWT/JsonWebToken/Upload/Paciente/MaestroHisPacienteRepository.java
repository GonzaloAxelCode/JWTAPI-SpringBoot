package com.JWT.JsonWebToken.Upload.Paciente;

import com.JWT.JsonWebToken.Upload.UbigeoReniec.MaestroHisUbigeoIneiReniec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaestroHisPacienteRepository  extends JpaRepository<MaestroHisPaciente, String> {
}
