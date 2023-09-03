package com.JWT.JsonWebToken.Upload.Establecimiento;

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
public class MaestroHisEstablecimiento {
    @Id
    @Column(name = "Id_Establecimiento")
    private String idEstablecimiento;

    @Column(name = "Nombre_Establecimiento")
    private String nombreEstablecimiento;

    @Column(name = "Ubigueo_Establecimiento")
    private Integer ubigueoEstablecimiento;

    @Column(name = "Codigo_Disa")
    private Integer codigoDisa;

    @Column(name = "Disa")
    private String disa;

    @Column(name = "Codigo_Red")
    private Integer codigoRed;

    @Column(name = "Red")
    private String red;

    @Column(name = "Codigo_MicroRed")
    private Integer codigoMicroRed;

    @Column(name = "MicroRed")
    private String microRed;

    @Column(name = "Codigo_Unico")
    private Integer codigoUnico;

    @Column(name = "Codigo_Sector")
    private Integer codigoSector;

    @Column(name = "Descripcion_Sector")
    private String descripcionSector;

    @Column(name = "Departamento")
    private String departamento;

    @Column(name = "Provincia")
    private String provincia;

    @Column(name = "Distrito")
    private String distrito;

    @Column(name = "Categoria_Establecimiento")
    private String categoriaEstablecimiento;
}
