package com.JWT.JsonWebToken.Upload.Cnv;

import java.time.LocalTime;

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
public class Cnv {
    @Id
    @Column(name = "CNV", length = 255)
    private String cnv;

    @Column(name = "Estado", length = 255)
    private String estado;

    @Column(name = "CodEESS", length = 255)
    private String codEESS;

    @Column(name = "EESS", length = 255)
    private String eess;

    @Column(name = "pApellidoMadre", length = 255)
    private String pApellidoMadre;

    @Column(name = "sApellidoMadre", length = 255)
    private String sApellidoMadre;

    @Column(name = "nombresMadre", length = 255)
    private String nombresMadre;

    @Column(name = "Edad")
    private Integer edad;

    @Column(name = "FecNac", length = 255)
    private String fecNac;

    @Column(name = "Gest_Sem")
    private Integer gestSem;

    @Column(name = "TipoDoc", length = 255)
    private String tipoDoc;

    @Column(name = "Documento", length = 255)
    private String documento;

    @Column(name = "Telefono", length = 255)
    private String telefono;

    @Column(name = "CodEESSPrenatal", length = 255)
    private String codEESSPrenatal;

    @Column(name = "EESSPrenatal", length = 255)
    private String eessPrenatal;

    @Column(name = "Fecha", length = 255)
    private String fecha;

    @Column(name = "Hora")
    private LocalTime hora;

    @Column(name = "Sexo", length = 255)
    private String sexo;

    @Column(name = "Peso_g")
    private Double pesoG;

    @Column(name = "Talla_cm")
    private Double tallaCm;

    @Column(name = "Apgar")
    private Double apgar;

    @Column(name = "Perimetrocefalico")
    private Double perimetrocefalico;

    @Column(name = "Perimetrotoracico")
    private Double perimetrotoracico;

    @Column(name = "Malfcongenita", length = 255)
    private String malfcongenita;

    @Column(name = "TiempoLigCord", length = 255)
    private String tiempoLigCord;

    @Column(name = "Lactanciaprecoz", length = 255)
    private String lactanciaprecoz;

    @Column(name = "pApellidoProfesional", length = 255)
    private String pApellidoProfesional;

    @Column(name = "sApellidoProfesional", length = 255)
    private String sApellidoProfesional;

    @Column(name = "nombresProfesional", length = 255)
    private String nombresProfesional;

    @Column(name = "Profesion", length = 255)
    private String profesion;

    @Column(name = "NColegio", length = 255)
    private String nColegio;

    @Column(name = "FechaRegistro", length = 255)
    private String fechaRegistro;

    @Column(name = "pApellidoResgistrador", length = 255)
    private String pApellidoResgistrador;

    @Column(name = "sApellidoRegistrador", length = 255)
    private String sApellidoRegistrador;

    @Column(name = "nombresRegistrador", length = 255)
    private String nombresRegistrador;

    @Column(name = "tipoPartoId")
    private Integer tipoPartoId;

    @Column(name = "tipoParto", length = 255)
    private String tipoParto;
}
