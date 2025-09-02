package co.edu.unicauca.asae_t3.capaAccesoADatos.models;

import java.util.List;

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

    List<FranjaHorariaEntity> franjasHorarias;

    public EspacioFisicoEntity(Integer idEspacioFisico, String nombre, String tipo, Integer capacidad, String ubicacion) {
        this.idEspacioFisico = idEspacioFisico;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.estado = true;
    }
}
