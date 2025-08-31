package co.edu.unicauca.asae_t3.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EspacioFisicoDTORespuesta {
    private Integer idEspacioFisico;
    private String nombre;
    private String tipo; // Aula, Laboratorio, Auditorio.
    private Integer capacidad;
    private String ubicacion;
    private Boolean estado; 
}
