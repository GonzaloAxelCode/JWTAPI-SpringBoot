package com.JWT.JsonWebToken.Upload.CitaNominal;

import com.JWT.JsonWebToken.Upload.CentroPoblado.MaestroCentroPoblado;
import com.JWT.JsonWebToken.Upload.Cpms.MaestroHisCieCpms;
import com.JWT.JsonWebToken.Upload.Establecimiento.MaestroHisEstablecimiento;
import com.JWT.JsonWebToken.Upload.Financiador.MaestroHisFinanciador;
import com.JWT.JsonWebToken.Upload.OtraCondicion.MaestroHisOtraCondicion;
import com.JWT.JsonWebToken.Upload.Paciente.MaestroHisPaciente;
import com.JWT.JsonWebToken.Upload.Pais.MaestroHisPais;
import com.JWT.JsonWebToken.Upload.Personal.MaestroHisPersonal;
import com.JWT.JsonWebToken.Upload.Registrador.MaestroHisRegistrador;
import com.JWT.JsonWebToken.Upload.Ups.MaestroUps;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class CitaNominal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Id_Cita")
    private String idCita;

    @Column(name = "Anio")
    private Integer anio;

    @Column(name = "Mes")
    private Integer mes;

    @Column(name = "Dia")
    private Integer dia;

    @Column(name = "Fecha_Atencion")
    private String fechaAtencion;

    @Column(name = "Lote")
    private String lote;

    @Column(name = "Num_Pag")
    private Integer numPag;

    @Column(name = "Num_Reg")
    private Integer numReg;

    @ManyToOne
    @JoinColumn(name = "Id_Ups")
    private MaestroUps ups;

    @ManyToOne
    @JoinColumn(name = "Id_Establecimiento")
    private MaestroHisEstablecimiento establecimiento;

    @ManyToOne
    @JoinColumn(name = "Id_Paciente")
    private MaestroHisPaciente paciente;

    @ManyToOne
    @JoinColumn(name = "Id_Personal")
    private MaestroHisPersonal personal;

    @ManyToOne
    @JoinColumn(name = "Id_Registrador")
    private MaestroHisRegistrador registrador;

    @ManyToOne
    @JoinColumn(name = "Id_Financiador")
    private MaestroHisFinanciador financiador;

    @Column(name = "Id_Condicion_Establecimiento")
    private String idCondicionEstablecimiento;

    @Column(name = "Id_Condicion_Servicio")
    private String idCondicionServicio;

    @Column(name = "Edad_Reg")
    private Integer edadReg;

    @Column(name = "Tipo_Edad")
    private String tipoEdad;

    @Column(name = "Anio_Actual_Paciente")
    private Integer anioActualPaciente;

    @Column(name = "Mes_Actual_Paciente")
    private Integer mesActualPaciente;

    @Column(name = "Dia_Actual_Paciente")
    private Integer diaActualPaciente;

    @Column(name = "Id_Turno")
    private String idTurno;

    @ManyToOne
    @JoinColumn(name = "Codigo_Item")
    private MaestroHisCieCpms cieCpms;

    @Column(name = "Tipo_Diagnostico")
    private String tipoDiagnostico;

    @Column(name = "Peso")
    private Float peso;

    @Column(name = "Talla")
    private Float talla;

    @Column(name = "Hemoglobina")
    private Float hemoglobina;

    @Column(name = "Perimetro_Abdominal")
    private Float perimetroAbdominal;

    @Column(name = "Perimetro_Cefalico")
    private Float perimetroCefalico;

    @ManyToOne
    @JoinColumn(name = "Id_Otra_Condicion")
    private MaestroHisOtraCondicion otraCondicion;

    @ManyToOne
    @JoinColumn(name = "Id_Centro_Poblado")
    private MaestroCentroPoblado centroPoblado;

    @Column(name = "Id_Correlativo")
    private Integer idCorrelativo;

    @Column(name = "Id_Correlativo_Lab")
    private Integer idCorrelativoLab;

    @Column(name = "Valor_Lab")
    private String valorLab;

    @Column(name = "Fecha_Ultima_Regla")
    private String fechaUltimaRegla;

    @Column(name = "Fecha_Solicitud_Hb")
    private String fechaSolicitudHb;

    @Column(name = "Fecha_Resultado_Hb")
    private String fechaResultadoHb;

    @Column(name = "Fecha_Registro")
    private String fechaRegistro;

    @Column(name = "Fecha_Modificacion")
    private String fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "Id_Pais")
    private MaestroHisPais pais;
}
