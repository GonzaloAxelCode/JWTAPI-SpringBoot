package com.JWT.JsonWebToken.Upload.Actividad;

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
public class MaestroHisActividad {
   @Id
    @Column(name = "Id_Actividad_His")
    private String idActividadHis;

    @Column(name = "Descripcion_Actividad_His")
    private String descripcionActividadHis;

    @Column(name = "Fg_Estado")
    private Integer fgEstado;

    
}
