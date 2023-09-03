package com.JWT.JsonWebToken.Upload.CentroPoblado;

import com.JWT.JsonWebToken.Upload.UbigeoReniec.MaestroHisUbigeoIneiReniec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CentroPobladoRepository extends JpaRepository<MaestroCentroPoblado, String> {
}
