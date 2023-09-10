package com.JWT.JsonWebToken.Upload.Paciente;

import com.JWT.JsonWebToken.Upload.Establecimiento.MaestroHisEstablecimiento;
import com.JWT.JsonWebToken.Upload.Etnia.MaestroHisEtnia;
import com.JWT.JsonWebToken.Upload.Pais.MaestroHisPais;
import com.JWT.JsonWebToken.Upload.TipoDoc.MaestroTipoDoc;

import com.JWT.JsonWebToken.Upload.UbigeoReniec.MaestroHisUbigeoIneiReniec;
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
public class MaestroHisPaciente {
        @Id
    @Column(name = "Id_Paciente", unique = true, nullable = false, length = 255)
    private String idPaciente;

    @ManyToOne
    @JoinColumn(name = "Id_Tipo_Documento", referencedColumnName = "Id_Tipo_Documento")
    private MaestroTipoDoc tipoDocumento;

    @Column(name = "Numero_Documento")
    private String numeroDocumento;

    @Column(name = "Apellido_Paterno_Paciente")
    private String apellidoPaternoPaciente;

    @Column(name = "Apellido_Materno_Paciente")
    private String apellidoMaternoPaciente;

    @Column(name = "Nombres_Paciente")
    private String nombresPaciente;

    @Column(name = "Fecha_Nacimiento")
    private String fechaNacimiento;

    @Column(name = "Genero")
    private String genero;

    @ManyToOne
    @JoinColumn(name = "Id_Etnia", referencedColumnName = "Id_Etnia")
    private MaestroHisEtnia etnia;

    @Column(name = "Historia_Clinica")
    private String historiaClinica;

    @Column(name = "Ficha_Familiar")
    private String fichaFamiliar;

    @Column(name = "Ubigeo_Nacimiento")
    private Integer ubigeoNacimiento;

    @ManyToOne
    @JoinColumn(name = "Ubigeo_Reniec",referencedColumnName = "id_ubigueo_reniec", unique = true, nullable = true)
    private MaestroHisUbigeoIneiReniec ubigeoReniec;

    @Column(name = "Domicilio_Reniec")
    private String domicilioReniec;

    @ManyToOne
    @JoinColumn(name = "Ubigeo_Declarado",referencedColumnName = "id_ubigueo_inei", unique = true, nullable =true)
    private MaestroHisUbigeoIneiReniec ubigeoDeclarado;

    @Column(name = "Domicilio_Declarado")
    private Integer domicilioDeclarado;

    @Column(name = "Referencia_Domicilio")
    private Integer referenciaDomicilio;

    @ManyToOne
    @JoinColumn(name = "Id_Pais", referencedColumnName = "Id_Pais")
    private MaestroHisPais pais;

    @ManyToOne
    @JoinColumn(name = "Id_Establecimiento", referencedColumnName = "Id_Establecimiento")
    private MaestroHisEstablecimiento establecimiento;

    @Column(name = "Fecha_Alta")
    private String fechaAlta;

    @Column(name = "Fecha_Modificacion")
    private String fechaModificacion;
}
