package com.JWT.JsonWebToken.Upload.Personal;

import com.JWT.JsonWebToken.Upload.UbigeoReniec.MaestroHisUbigeoIneiReniec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaestroHisPersonalRepository  extends JpaRepository<MaestroHisPersonal, String> {
}
