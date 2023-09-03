package com.JWT.JsonWebToken.Upload.Cpms;

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
public class MaestroHisCieCpms {
    @Id
    @Column(name = "Codigo_Item")
    private String codigoItem;

    @Column(name = "Descripcion_Item")
    private String descripcionItem;

    @Column(name = "Fg_Tipo")
    private String fgTipo;

    @Column(name = "Descripcion_Tipo_Item")
    private String descripcionTipoItem;

    @Column(name = "Fg_Estado")
    private Integer fgEstado;
}
