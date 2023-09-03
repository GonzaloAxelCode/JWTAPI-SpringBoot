package com.JWT.JsonWebToken.Upload.CondicionContrato;

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
public class MaestroCondicionContrato {
    @Id
    @Column(name = "Id_Condicion")
    private Long idCondicion;

    @Column(name = "Descripcion_Condicion")
    private String descripcionCondicion;
}
