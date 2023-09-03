package com.JWT.JsonWebToken.Upload.Pais;

import com.JWT.JsonWebToken.Upload.UbigeoReniec.MaestroHisUbigeoIneiReniec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaestroHisPaisRepository extends JpaRepository<MaestroHisPais, String> {
}
