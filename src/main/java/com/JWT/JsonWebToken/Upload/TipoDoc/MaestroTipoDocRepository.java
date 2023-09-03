package com.JWT.JsonWebToken.Upload.TipoDoc;

import com.JWT.JsonWebToken.Upload.UbigeoReniec.MaestroHisUbigeoIneiReniec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaestroTipoDocRepository extends JpaRepository<MaestroTipoDoc, String> {
}
