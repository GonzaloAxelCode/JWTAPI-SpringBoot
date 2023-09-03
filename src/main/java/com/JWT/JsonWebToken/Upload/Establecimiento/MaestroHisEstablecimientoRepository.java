package com.JWT.JsonWebToken.Upload.Establecimiento;

import com.JWT.JsonWebToken.Upload.UbigeoReniec.MaestroHisUbigeoIneiReniec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MaestroHisEstablecimientoRepository extends JpaRepository<MaestroHisEstablecimiento, String> {
}
