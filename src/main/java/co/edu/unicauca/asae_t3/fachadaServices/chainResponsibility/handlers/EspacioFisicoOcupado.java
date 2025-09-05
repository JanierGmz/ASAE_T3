
package co.edu.unicauca.asae_t3.fachadaServices.chainResponsibility.handlers;

import co.edu.unicauca.asae_t3.fachadaServices.DTO.FranjaHorariaDTOPeticion;
import co.edu.unicauca.asae_t3.fachadaServices.chainResponsibility.chain.SolicitudFranjaHoraria;

import co.edu.unicauca.asae_t3.fachadaServices.exceptions.FormatoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import co.edu.unicauca.asae_t3.capaAccesoADatos.repositories.EspacioFisicoRepository;

@Component
public class EspacioFisicoOcupado extends SolicitudFranjaHoraria{

    @Autowired
    @Qualifier("IDEspacioFisicoRepository")
    private EspacioFisicoRepository espacioFisicoRepository;


    @Override
    public boolean procesarSolicitud(FranjaHorariaDTOPeticion solicitudFranjaHoraria) {
        Integer idEspacioFisico = solicitudFranjaHoraria.getIdEspacioFisico();
        if (idEspacioFisico != null) {
            var espacioOpt = espacioFisicoRepository.findById(idEspacioFisico);
            if (espacioOpt.isPresent()) {
                var espacio = espacioOpt.get();
                if (espacio.getFranjasHorarias() != null) {
                    for (var franja : espacio.getFranjasHorarias()) {
                        // Validar si hay cruce de día y horario
                        if (franja.getDia().equals(solicitudFranjaHoraria.getDia())) {
                            var inicioNueva = solicitudFranjaHoraria.getHoraInicio();
                            var finNueva = solicitudFranjaHoraria.getHoraFin();
                            var inicioExistente = franja.getHoraInicio();
                            var finExistente = franja.getHoraFin();
                            // Si hay traslape de horarios
                            if (!(finNueva.isBefore(inicioExistente) || inicioNueva.isAfter(finExistente))) {
                                // Ocupado
                                throw new FormatoException("El espacio físico ya está ocupado en la franja horaria solicitada.");
                            }
                        }
                    }
                }
            }
        }
        // Si no está ocupado, sigue con el siguiente manejador en la cadena
        if (this.getSiguiente() != null) {
            return this.getSiguiente().procesarSolicitud(solicitudFranjaHoraria);
        }
        return true;
    }

}
