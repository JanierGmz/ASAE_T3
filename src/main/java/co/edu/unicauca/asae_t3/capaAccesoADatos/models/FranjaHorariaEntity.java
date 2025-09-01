package co.edu.unicauca.asae_t3.capaAccesoADatos.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FranjaHorariaEntity {
    private Integer idFranjaHoraria;
    private String dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Boolean estado;

    private CursoEntity curso;
    private EspacioFisicoEntity espacioFisico;
    private List<DocenteEntity> docentes;

    // Constructor para inicialización rápida (un solo docente)
    public FranjaHorariaEntity(Integer idFranjaHoraria, String horaInicio, String horaFin, String dia, Boolean estado, CursoEntity curso, EspacioFisicoEntity espacioFisico, DocenteEntity docente) {
        this.idFranjaHoraria = idFranjaHoraria;
        this.dia = dia;
        this.horaInicio = java.time.LocalTime.parse(horaInicio);
        this.horaFin = java.time.LocalTime.parse(horaFin);
        this.estado = estado;
        this.curso = curso;
        this.espacioFisico = espacioFisico;
        this.docentes = new java.util.ArrayList<>();
        this.docentes.add(docente);
    }
}
