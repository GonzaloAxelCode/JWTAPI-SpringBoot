package com.JWT.JsonWebToken.Upload.TipoDoc;


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
public class MaestroTipoDoc {
     @Id
    @Column(name = "Id_Tipo_Documento")
    private Integer idTipoDocumento;

    @Column(name = "Abrev_Tipo_Doc")
    private String abrevTipoDoc;

    @Column(name = "Descripcion_Tipo_Documento")
    private String descripcionTipoDocumento;
}
