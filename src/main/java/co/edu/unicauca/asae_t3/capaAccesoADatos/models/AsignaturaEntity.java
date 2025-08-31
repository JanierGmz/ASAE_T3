package co.edu.unicauca.asae_t3.capaAccesoADatos.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturaEntity {
    private Integer idAsignatura;
    private String nombre;
    private String codigo;
    private Integer numeroCreditos;
    private Integer horasSemanales;
    private String descripcion;
    private Boolean estado; 
}
