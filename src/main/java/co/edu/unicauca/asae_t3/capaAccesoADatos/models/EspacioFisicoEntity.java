package co.edu.unicauca.asae_t3.capaAccesoADatos.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EspacioFisicoEntity {
    private Integer idEspacioFisico;
    private String nombre;
    private String tipo; // Aula, Laboratorio, Autitorio.
    private Integer capacidad;
    private String ubicacion;
    private Boolean estado;
}
