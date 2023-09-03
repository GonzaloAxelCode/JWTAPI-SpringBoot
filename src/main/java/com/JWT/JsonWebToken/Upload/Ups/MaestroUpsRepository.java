package com.JWT.JsonWebToken.Upload.Ups;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaestroUpsRepository extends JpaRepository<MaestroUps, String> {
}
