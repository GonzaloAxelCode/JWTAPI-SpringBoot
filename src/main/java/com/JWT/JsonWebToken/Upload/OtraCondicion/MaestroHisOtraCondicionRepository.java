package com.JWT.JsonWebToken.Upload.OtraCondicion;

import com.JWT.JsonWebToken.Upload.UbigeoReniec.MaestroHisUbigeoIneiReniec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface MaestroHisOtraCondicionRepository extends JpaRepository<MaestroHisOtraCondicion, String> {
}
