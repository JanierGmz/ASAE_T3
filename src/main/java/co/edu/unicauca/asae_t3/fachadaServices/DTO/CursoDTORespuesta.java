package co.edu.unicauca.asae_t3.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTORespuesta {
    private Integer idCurso;
    private String nombre;
    private String codigo;

    private Integer idAsignatura;
}
