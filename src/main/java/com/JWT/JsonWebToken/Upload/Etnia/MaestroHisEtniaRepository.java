package com.JWT.JsonWebToken.Upload.Etnia;

import com.JWT.JsonWebToken.Upload.UbigeoReniec.MaestroHisUbigeoIneiReniec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MaestroHisEtniaRepository  extends JpaRepository<MaestroHisEtnia, String> {
}
