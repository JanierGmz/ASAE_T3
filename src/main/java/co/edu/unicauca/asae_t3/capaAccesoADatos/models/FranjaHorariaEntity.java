package co.edu.unicauca.asae_t3.capaAccesoADatos.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;

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

    @JsonBackReference
    private CursoEntity curso;
    @JsonBackReference
    private EspacioFisicoEntity espacioFisico;
    @JsonBackReference
    private List<DocenteEntity> docentes;

    public FranjaHorariaEntity(Integer idFranjaHoraria, String dia, LocalTime horaInicio, LocalTime horaFin) {
        this.idFranjaHoraria = idFranjaHoraria;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = true;
    }

}
