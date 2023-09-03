package com.JWT.JsonWebToken.Upload.Registrador;


import com.JWT.JsonWebToken.Upload.UbigeoReniec.MaestroHisUbigeoIneiReniec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaestroHisRegistradorRepository  extends JpaRepository<MaestroHisRegistrador, String> {
}
