package co.edu.unicauca.asae_t3.fachadaServices.DTO;

import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FranjaHorariaDTOPeticion {
    private String dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    private CursoDTOPeticion curso;
    private EspacioFisicoDTOPeticion espacioFisico;
    private List<DocenteDTOPeticion> docentes;
}
