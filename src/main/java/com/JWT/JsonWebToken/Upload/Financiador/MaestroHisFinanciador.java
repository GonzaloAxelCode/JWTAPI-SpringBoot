package com.JWT.JsonWebToken.Upload.Financiador;

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
public class MaestroHisFinanciador {
     @Id
    @Column(name = "Id_Financiador")
    private Integer idFinanciador;

    @Column(name = "Descripcion_Financiador")
    private String descripcionFinanciador;
}
