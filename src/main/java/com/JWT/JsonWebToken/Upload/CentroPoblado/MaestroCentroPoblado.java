package com.JWT.JsonWebToken.Upload.CentroPoblado;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MaestroCentroPoblado {

    @Id
    @Column(name = "Id_Centro_Poblado")
    private Long idCentroPoblado;

    @Column(name = "Descripcion_Centro_Poblado")
    private String descripcionCentroPoblado;
    
    @Column(name = "Id_Codigo_Centro_Poblado")
    private Long idCodigoCentroPoblado;

    @Column(name = "Id_Ubigueo_Centro_Poblado")
    private Long idUbigueoCentroPoblado;

    @Column(name = "Altitud_Centro_Poblado")
    private Double altitudCentroPoblado;
}
