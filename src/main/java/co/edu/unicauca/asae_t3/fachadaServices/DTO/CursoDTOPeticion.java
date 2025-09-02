package co.edu.unicauca.asae_t3.fachadaServices.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTOPeticion {
    private String codigo;
    private String nombre;
    private Integer cantidadCupos;

    private AsignaruraDTOPeticion asignatura;
    private List<FranjaHorariaDTOPeticion> franjasHorarias;
}
