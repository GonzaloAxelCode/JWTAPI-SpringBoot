package com.JWT.JsonWebToken.Upload.Ups;


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
public class MaestroUps {
    @Id
    @Column(name = "Id_Ups")
    private String idUps;

    @Column(name = "Descripcion_Ups")
    private String descripcionUps;
}
