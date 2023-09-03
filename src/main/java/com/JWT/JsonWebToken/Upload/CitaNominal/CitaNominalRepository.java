package com.JWT.JsonWebToken.Upload.CitaNominal;

import com.JWT.JsonWebToken.Upload.UbigeoReniec.MaestroHisUbigeoIneiReniec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CitaNominalRepository extends JpaRepository<CitaNominal, Integer> {
}
