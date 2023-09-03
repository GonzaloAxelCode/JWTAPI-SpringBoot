package com.JWT.JsonWebToken.Upload.Financiador;

import com.JWT.JsonWebToken.Upload.UbigeoReniec.MaestroHisUbigeoIneiReniec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MaestroHisFinanciadorRepository extends JpaRepository<MaestroHisFinanciador, String> {
}
