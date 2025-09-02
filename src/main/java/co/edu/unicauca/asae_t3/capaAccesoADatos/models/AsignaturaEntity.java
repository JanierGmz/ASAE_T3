package co.edu.unicauca.asae_t3.capaAccesoADatos.models;

import java.util.List;

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

    private List<CursoEntity> cursos;
    
    public AsignaturaEntity(Integer idAsignatura, String nombre, String codigo, Integer numeroCreditos, Integer horasSemanales, String descripcion) {
        this.idAsignatura = idAsignatura;
        this.nombre = nombre;
        this.codigo = codigo;
        this.numeroCreditos = numeroCreditos;
        this.horasSemanales = horasSemanales;
        this.descripcion = descripcion;
        this.estado = true;
    }
}
