package com.JWT.JsonWebToken.Upload.Personal;

import com.JWT.JsonWebToken.Upload.Colegio.MaestroHisColegio;
import com.JWT.JsonWebToken.Upload.CondicionContrato.MaestroCondicionContrato;
import com.JWT.JsonWebToken.Upload.Establecimiento.MaestroHisEstablecimiento;
import com.JWT.JsonWebToken.Upload.Profesion.MaestroHisProfesion;
import com.JWT.JsonWebToken.Upload.TipoDoc.MaestroTipoDoc;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MaestroHisPersonal {
    @Id
    @Column(name = "Id_Personal", length = 255)
    private String idPersonal;

    @ManyToOne
    @JoinColumn(name = "Id_Tipo_Documento", referencedColumnName = "Id_Tipo_Documento")
    private MaestroTipoDoc tipoDocumento;

    @Column(name = "Numero_Documento", length = 50)
    private String numeroDocumento;

    @Column(name = "Apellido_Paterno_Personal", length = 50)
    private String apellidoPaternoPersonal;

    @Column(name = "Apellido_Materno_Personal", length = 50)
    private String apellidoMaternoPersonal;

    @Column(name = "Nombres_Personal", length = 50)
    private String nombresPersonal;

    @Column(name = "Fecha_Nacimiento", length = 255)
    private String fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "Id_Condicion", referencedColumnName = "Id_Condicion")
    private MaestroCondicionContrato condicion;

    @ManyToOne
    @JoinColumn(name = "Id_Profesion", referencedColumnName = "Id_Profesion")
    private MaestroHisProfesion profesion;

    @ManyToOne
    @JoinColumn(name = "Id_Colegio", referencedColumnName = "Id_Colegio")
    private MaestroHisColegio colegio;

    @ManyToOne
    @JoinColumn(name = "Id_Establecimiento", referencedColumnName = "Id_Establecimiento",nullable = true)
    private MaestroHisEstablecimiento establecimiento;

    @Column(name = "Numero_Colegiatura", length = 255)
    private String numeroColegiatura;

    @Column(name = "Fecha_Alta", length = 255)
    private String fechaAlta;

    @Column(name = "Fecha_Baja", length = 255)
    private String fechaBaja;
}
