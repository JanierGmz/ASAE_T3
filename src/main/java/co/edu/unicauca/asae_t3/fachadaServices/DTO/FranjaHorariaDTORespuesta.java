package co.edu.unicauca.asae_t3.fachadaServices.DTO;

import co.edu.unicauca.asae_t3.capaAccesoADatos.models.DocenteEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FranjaHorariaDTORespuesta {
    private String dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Boolean estado;

    private Integer idCurso;
    private Integer idEspacioFisico;
    private List<DocenteEntity> docentes;
}
