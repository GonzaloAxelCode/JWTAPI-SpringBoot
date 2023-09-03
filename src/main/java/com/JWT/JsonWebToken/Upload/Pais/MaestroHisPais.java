package com.JWT.JsonWebToken.Upload.Pais;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MaestroHisPais {
        @Id
    @Column(name = "Id_Pais")
    private String idPais;

    @Column(name = "Descripcion_Pais")
    private String descripcionPais;
}
