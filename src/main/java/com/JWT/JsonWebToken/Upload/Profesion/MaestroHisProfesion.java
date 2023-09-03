package com.JWT.JsonWebToken.Upload.Profesion;

import com.JWT.JsonWebToken.Upload.Colegio.MaestroHisColegio;

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
public class MaestroHisProfesion {

      @Id
    @Column(name = "Id_Profesion")
    private Long idProfesion;

    @Column(name = "Descripcion_Profesion")
    private String descripcionProfesion;

    @ManyToOne
    @JoinColumn(name = "Id_Colegio")
    private MaestroHisColegio colegio;
}
