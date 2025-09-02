package co.edu.unicauca.asae_t3.capaAccesoADatos.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoEntity {
    private Integer idCurso;
    private String codigo;
    private String nombre;
    private Integer cantidadCupos;

    private AsignaturaEntity asignatura;
    private List<FranjaHorariaEntity> franjasHorarias;

    public CursoEntity(Integer idCurso, String codigo, String nombre, Integer cantidadCupos) {
        this.idCurso = idCurso;
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidadCupos = cantidadCupos;
    }
}
