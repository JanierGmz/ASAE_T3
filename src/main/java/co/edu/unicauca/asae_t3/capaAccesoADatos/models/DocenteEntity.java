package co.edu.unicauca.asae_t3.capaAccesoADatos.models;

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
}
