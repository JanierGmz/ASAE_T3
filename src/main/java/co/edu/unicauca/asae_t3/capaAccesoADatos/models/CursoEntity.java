package co.edu.unicauca.asae_t3.capaAccesoADatos.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoEntity {
    private Integer idCurso;
    private String nombre;
    private String codigo;

    private AsignaturaEntity asignatura;
}
