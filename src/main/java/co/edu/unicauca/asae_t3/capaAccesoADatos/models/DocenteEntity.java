package co.edu.unicauca.asae_t3.capaAccesoADatos.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocenteEntity {
    private Integer idDocente;
    private String nombres;
    private String apellidos;
    private String correoInstitucional;
    private String categoria; // planta, catedrático, ocasional
    private Boolean estado;

    private List<FranjaHorariaEntity> franjasHorarias;

    public DocenteEntity(Integer idDocente, String nombres, String apellidos, String correoInstitucional, String categoria) {
        this.idDocente = idDocente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoInstitucional = correoInstitucional;
        this.categoria = categoria;
        this.estado = true;
    }
}
