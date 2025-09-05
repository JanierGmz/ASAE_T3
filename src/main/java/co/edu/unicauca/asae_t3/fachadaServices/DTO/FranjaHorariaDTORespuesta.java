package co.edu.unicauca.asae_t3.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FranjaHorariaDTORespuesta {
    private Integer idFranjaHoraria;
    private String dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Boolean estado;
    
    private CursoDTORespuesta curso;
    /* 
    private Integer idCurso;
    private String nombreCurso;*/

    
    private EspacioFisicoDTORespuesta espacioFisico;

    /*
    private Integer idEspacioFisico;
    private String nombreEspacioFisico;*/
    
    private List<DocenteDTORespuesta> docentes;
}
