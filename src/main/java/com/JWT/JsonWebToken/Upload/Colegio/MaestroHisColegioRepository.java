package com.JWT.JsonWebToken.Upload.Colegio;

import com.JWT.JsonWebToken.Upload.UbigeoReniec.MaestroHisUbigeoIneiReniec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MaestroHisColegioRepository extends JpaRepository<MaestroHisColegio, Long> {
}
