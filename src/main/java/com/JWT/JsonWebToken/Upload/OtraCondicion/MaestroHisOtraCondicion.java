package com.JWT.JsonWebToken.Upload.OtraCondicion;

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
public class MaestroHisOtraCondicion {
        @Id
    @Column(name = "Id_Otra_Condicion")
    private  Integer idOtraCondicion;

    @Column(name = "Descripcion_Otra_Condicion")
    private String descripcionOtraCondicion;
}
