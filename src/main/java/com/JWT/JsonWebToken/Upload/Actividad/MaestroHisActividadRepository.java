package com.JWT.JsonWebToken.Upload.Actividad;

import com.JWT.JsonWebToken.Upload.UbigeoReniec.MaestroHisUbigeoIneiReniec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MaestroHisActividadRepository  extends JpaRepository<MaestroHisActividad, String> {
}
