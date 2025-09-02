package co.edu.unicauca.asae_t3.fachadaServices.chain;

import co.edu.unicauca.asae_t3.fachadaServices.DTO.FranjaHorariaDTOPeticion;
import lombok.Data;

@Data
public abstract class SolicitudFranjaHoraria {
    private SolicitudFranjaHoraria siguiente;

    public abstract boolean procesarSolicitud(FranjaHorariaDTOPeticion solicitudFranjaHoraria);
}
