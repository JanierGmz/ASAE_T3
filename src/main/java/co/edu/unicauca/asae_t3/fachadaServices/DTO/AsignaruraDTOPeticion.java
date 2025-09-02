package co.edu.unicauca.asae_t3.fachadaServices.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignaruraDTOPeticion {
    private String nombre;
    private String codigo;
    private Integer numeroCreditos;
    private Integer horasSemanales;
    private String descripcion;

    private List<CursoDTOPeticion> cursos;
}
