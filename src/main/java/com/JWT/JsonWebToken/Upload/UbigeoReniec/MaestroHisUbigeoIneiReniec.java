package com.JWT.JsonWebToken.Upload.UbigeoReniec;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MaestroHisUbigeoIneiReniec {

    @Id
    @Column(name = "Id_Ubigueo_Inei")
    private Integer idUbigueoInei;

    @Column(name = "Id_Ubigueo_Reniec")
    private Integer idUbigueoReniec;

    @Column(name = "Departamento")
    private String departamento;

    @Column(name = "Provincia")
    private String provincia;

    @Column(name = "Distrito")
    private String distrito;

    @Column(name = "Codigo_Departamento_Inei")
    private Integer codigoDepartamentoInei;

    @Column(name = "Codigo_Provincia_Inei")
    private Integer codigoProvinciaInei;

    @Column(name = "Codigo_Distrito_Inei")
    private Integer codigoDistritoInei;

    @Column(name = "Codigo_Departamento_Reniec")
    private Integer codigoDepartamentoReniec;

    @Column(name = "Codigo_Provincia_Reniec")
    private Integer codigoProvinciaReniec;

    @Column(name = "Codigo_Distrito_Reniec")
    private Integer codigoDistritoReniec;



}
