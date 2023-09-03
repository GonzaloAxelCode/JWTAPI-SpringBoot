package com.JWT.JsonWebToken.Upload.Etnia;

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
public class MaestroHisEtnia {
      @Id
    @Column(name = "Id_Etnia")
    private String idEtnia;

    @Column(name = "Descripcion_Etnia")
    private String descripcionEtnia;
}
