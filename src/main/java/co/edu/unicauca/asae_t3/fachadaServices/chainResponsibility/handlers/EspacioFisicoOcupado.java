
package co.edu.unicauca.asae_t3.fachadaServices.chainResponsibility.handlers;

import co.edu.unicauca.asae_t3.fachadaServices.DTO.FranjaHorariaDTOPeticion;
import co.edu.unicauca.asae_t3.fachadaServices.chainResponsibility.chain.SolicitudFranjaHoraria;

import org.springframework.beans.factory.annotation.Qualifier;

import co.edu.unicauca.asae_t3.capaAccesoADatos.repositories.EspacioFisicoRepository;

public class EspacioFisicoOcupado extends SolicitudFranjaHoraria{

    @Qualifier("IDEspacioFisicoRepository")
    private EspacioFisicoRepository espacioFisicoRepository;

    public EspacioFisicoOcupado(EspacioFisicoRepository espacioFisicoRepository) {
        this.espacioFisicoRepository = espacioFisicoRepository;
    }

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
                                return false;
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
