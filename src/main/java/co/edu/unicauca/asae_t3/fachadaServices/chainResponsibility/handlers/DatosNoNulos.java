package co.edu.unicauca.asae_t3.fachadaServices.chainResponsibility.handlers;

import org.springframework.stereotype.Component;

import co.edu.unicauca.asae_t3.fachadaServices.DTO.FranjaHorariaDTOPeticion;
import co.edu.unicauca.asae_t3.fachadaServices.chainResponsibility.chain.SolicitudFranjaHoraria;

@Component
public class DatosNoNulos extends SolicitudFranjaHoraria {

    @Override
    public boolean procesarSolicitud(FranjaHorariaDTOPeticion solicitudFranjaHoraria) {
        if (solicitudFranjaHoraria == null
                || solicitudFranjaHoraria.getDia() == null
                || solicitudFranjaHoraria.getHoraInicio() == null
                || solicitudFranjaHoraria.getHoraFin() == null
                || solicitudFranjaHoraria.getIdCurso() == null
                || solicitudFranjaHoraria.getIdEspacioFisico() == null
                || solicitudFranjaHoraria.getIdDocentes() == null) {
            return false;
        }
        
        if (this.getSiguiente() != null) {
            return this.getSiguiente().procesarSolicitud(solicitudFranjaHoraria);
        }
        System.out.println("Validación de datos no nulos pasada.");
        return true;
    }

}
