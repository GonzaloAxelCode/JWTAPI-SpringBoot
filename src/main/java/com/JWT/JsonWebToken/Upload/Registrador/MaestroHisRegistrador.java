package com.JWT.JsonWebToken.Upload.Registrador;

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
public class MaestroHisRegistrador {
    @Id
    @Column(name = "Id_Registrador")
    private String idRegistrador;

    @ManyToOne
    @JoinColumn(name = "Id_Tipo_Documento")
    private MaestroTipoDoc tipoDocumento;

    @Column(name = "Numero_Documento")
    private String numeroDocumento;

    @Column(name = "Apellido_Paterno_Registrador")
    private String apellidoPaternoRegistrador;

    @Column(name = "Apellido_Materno_Registrador")
    private String apellidoMaternoRegistrador;

    @Column(name = "Nombres_Registrador")
    private String nombresRegistrador;

    @Column(name = "Fecha_Nacimiento")
    private String fechaNacimiento;
}
