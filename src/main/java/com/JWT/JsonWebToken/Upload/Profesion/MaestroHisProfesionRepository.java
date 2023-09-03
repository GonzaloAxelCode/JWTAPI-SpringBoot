package com.JWT.JsonWebToken.Upload.Profesion;

import com.JWT.JsonWebToken.Upload.UbigeoReniec.MaestroHisUbigeoIneiReniec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MaestroHisProfesionRepository extends JpaRepository<MaestroHisProfesion, Long> {
}
