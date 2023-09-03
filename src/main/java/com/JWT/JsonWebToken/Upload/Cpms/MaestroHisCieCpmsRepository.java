package com.JWT.JsonWebToken.Upload.Cpms;

import com.JWT.JsonWebToken.Upload.UbigeoReniec.MaestroHisUbigeoIneiReniec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaestroHisCieCpmsRepository extends JpaRepository<MaestroHisCieCpms,String> {
}
