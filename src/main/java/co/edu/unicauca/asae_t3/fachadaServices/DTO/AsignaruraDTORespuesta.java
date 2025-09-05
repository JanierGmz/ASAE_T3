package co.edu.unicauca.asae_t3.fachadaServices.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignaruraDTORespuesta {
    private Integer idAsignatura;
    private String nombre;
    private String codigo;
    private Integer numeroCreditos;
    private Integer horasSemanales;
    private String descripcion;
    private Boolean estado;
    
    private List<CursoDTORespuesta> cursos;
}
