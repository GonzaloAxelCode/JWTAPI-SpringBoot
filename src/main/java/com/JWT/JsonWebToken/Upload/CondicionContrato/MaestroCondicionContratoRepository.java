package com.JWT.JsonWebToken.Upload.CondicionContrato;

import com.JWT.JsonWebToken.Upload.UbigeoReniec.MaestroHisUbigeoIneiReniec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaestroCondicionContratoRepository extends JpaRepository<MaestroCondicionContrato, Long> {
}
