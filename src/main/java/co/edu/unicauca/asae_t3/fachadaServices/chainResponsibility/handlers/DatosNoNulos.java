package co.edu.unicauca.asae_t3.fachadaServices.chainResponsibility.handlers;

import org.springframework.stereotype.Component;

import co.edu.unicauca.asae_t3.fachadaServices.DTO.FranjaHorariaDTOPeticion;
import co.edu.unicauca.asae_t3.fachadaServices.chainResponsibility.chain.SolicitudFranjaHoraria;
import co.edu.unicauca.asae_t3.fachadaServices.exceptions.FranjaException;

@Component
public class DatosNoNulos extends SolicitudFranjaHoraria {

    @Override
    public boolean procesarSolicitud(FranjaHorariaDTOPeticion solicitudFranjaHoraria) {
        if (solicitudFranjaHoraria == null) {
            throw new FranjaException("La solicitud de franja horaria no puede ser nula.");
        }
        if (solicitudFranjaHoraria.getDia() == null) {
            throw new FranjaException("El campo 'dia' no puede ser nulo.");
        }
        if (solicitudFranjaHoraria.getHoraInicio() == null) {
            throw new FranjaException("El campo 'horaInicio' no puede ser nulo.");
        }
        if (solicitudFranjaHoraria.getHoraFin() == null) {
            throw new FranjaException("El campo 'horaFin' no puede ser nulo.");
        }
        if (solicitudFranjaHoraria.getIdCurso() == null) {
            throw new FranjaException("El campo 'idCurso' no puede ser nulo.");
        }
        if (solicitudFranjaHoraria.getIdEspacioFisico() == null) {
            throw new FranjaException("El campo 'idEspacioFisico' no puede ser nulo.");
        }
        if (solicitudFranjaHoraria.getIdDocentes() == null) {
            throw new FranjaException("El campo 'idDocentes' no puede ser nulo.");
        }
        // Si todo está correcto, pasa al siguiente manejador en la cadena
        if (this.getSiguiente() != null) {
            return this.getSiguiente().procesarSolicitud(solicitudFranjaHoraria);
        }
        return true;
    }

}
